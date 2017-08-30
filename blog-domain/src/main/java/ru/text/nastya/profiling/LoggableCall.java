package ru.text.nastya.profiling;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoggableCall {

    LogLevel logLevel() default LogLevel.INFO;

    boolean showArgs() default false;

    boolean timeRecord() default false;

    boolean showOutput() default false;

}
