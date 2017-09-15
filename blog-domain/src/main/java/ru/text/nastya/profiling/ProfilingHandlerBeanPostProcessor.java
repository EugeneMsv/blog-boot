package ru.text.nastya.profiling;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.management.*;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Pair<Profiling, Class>> profilingClasses = new HashMap<>();

    private final ProfilingController profilingController = new ProfilingController();

    private static final Logger logger = LoggerFactory.getLogger(ProfilingHandlerBeanPostProcessor.class);

    private static final Predicate<Profiling> isTimeRecordEnabled = (x) -> x.timeRecord() && logger.isTraceEnabled();

    private static final ObjectName profilingMBeanName = createObjectName();

    private static ObjectName createObjectName() {
        try {
            return new ObjectName("profiling", "name", "controller");
        } catch (MalformedObjectNameException e) {
            throw new RuntimeException(e);
        }
    }

    public ProfilingHandlerBeanPostProcessor() {
        tryRegisterMbean();
    }

    private void tryRegisterMbean() {
        try {
            MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
            if (!platformMBeanServer.isRegistered(profilingMBeanName)) {
                platformMBeanServer.registerMBean(profilingController, profilingMBeanName);
            }
        } catch (InstanceAlreadyExistsException | NotCompliantMBeanException | MBeanRegistrationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)) {
            profilingClasses.put(beanName, new ImmutablePair<>(beanClass.getAnnotation(Profiling.class), beanClass));
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Pair<Profiling, Class> annotationBeanPair = profilingClasses.get(beanName);
        if (annotationBeanPair != null) {
            Class beanClass = annotationBeanPair.getRight();
            Profiling annotation = annotationBeanPair.getLeft();
            return Proxy
                    .newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
                        if (profilingController.isEnabled()) {
                            logBeforeMethod(annotation, beanClass, method, args);

                            Optional<Instant> startTime = startWatch(annotation);

                            Object retValue = method.invoke(bean, args);

                            logWatch(beanClass, annotation, method, startTime);

                            logAfterMethod(annotation, beanClass, method, retValue);

                            return retValue;
                        }
                        return method.invoke(bean, args);

                    });
        }
        return bean;
    }

    private void logWatch(Class beanClass, Profiling annotation, Method method, Optional<Instant> startTime) {
        if (isTimeRecordEnabled.test(annotation)) {
            startTime.ifPresent(instant -> logger.trace("Called='{}'; method='{}'; Time: {}",
                    beanClass.getName(), method.getName(), Duration.between(instant, Instant.now())));
        }
    }

    private Optional<Instant> startWatch(Profiling annotation) {
        Optional<Instant> startTime = Optional.empty();
        if (isTimeRecordEnabled.test(annotation)) {
            startTime = Optional.of(Instant.now());
        }
        return startTime;
    }

    private void logBeforeMethod(Profiling annotation, Class beanClass, Method method, Object[] args) {
        LogLevel logLevel = annotation.logLevel();
        if (logLevel.isEnabled(logger)) {
            String methodName = method.getName();
            if (annotation.showArgs()) {
                logLevel.callLogger(logger, beanClass, methodName, args);
            } else {
                logLevel.callLogger(logger, beanClass, methodName);
            }
        }
    }

    private void logAfterMethod(Profiling annotation, Class beanClass, Method method, Object retValue) {
        LogLevel logLevel = annotation.logLevel();
        if (annotation.showOutput() && logLevel.isEnabled(logger)) {
            logLevel.callLogger(logger, beanClass, method.getName(), retValue);
        }
    }
}
