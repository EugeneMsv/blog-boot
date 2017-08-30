package ru.text.nastya.profiling;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Profiling {

    LogLevel logLevel() default LogLevel.INFO;

    boolean showArgs() default false;

    boolean timeRecord() default false;

    boolean showOutput() default false;
}
