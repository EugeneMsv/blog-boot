package ru.text.nastya.profiling;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

/**
 * Устаревший т.к. через BeanPostProcessor прирост производительности x10 при вызове методов
 */
@Deprecated
@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(loggableCall)")
    public void loggableMethods(LoggableCall loggableCall) {
    }

    @Pointcut("execution(public * *(..))")
    public void allMethods() {
    }

    @Around("loggableMethods(loggableCall) && allMethods()")
    public Object adviseAnnotatedMethods(ProceedingJoinPoint pjp, LoggableCall loggableCall) throws Throwable {

        logBeforeMethod(pjp, loggableCall);

        Instant startInstant = null;
        if (loggableCall.timeRecord() && logger.isTraceEnabled()) {
            startInstant = Instant.now();
        }

        Object proceed = pjp.proceed();

        if (loggableCall.timeRecord() && logger.isTraceEnabled() && startInstant != null) {
            logger.trace("Time: {}", Duration.between(startInstant, Instant.now()));
        }

        logAfterMethod(pjp, proceed, loggableCall);
        return proceed;

    }

    private void logBeforeMethod(ProceedingJoinPoint pjp, LoggableCall loggableCall) {
        LogLevel logLevel = loggableCall.logLevel();
        if (logLevel.isEnabled(logger)) {
            Class<?> loggedClass = pjp.getTarget().getClass();
            String methodName = pjp.getSignature().getName();
            if (loggableCall.showArgs()) {
                logLevel.callLogger(logger, loggedClass, methodName, pjp.getArgs());
            } else {
                logLevel.callLogger(logger, loggedClass, methodName);
            }
        }
    }

    private void logAfterMethod(ProceedingJoinPoint pjp, Object proceed, LoggableCall loggableCall) {
        LogLevel logLevel = loggableCall.logLevel();
        if (loggableCall.showOutput() && logLevel.isEnabled(logger)) {
            Class<?> loggedClass = pjp.getTarget().getClass();
            String methodName = pjp.getSignature().getName();
            logLevel.callLogger(logger, loggedClass, methodName, proceed);
        }
    }
}
