package ru.text.nastya.validation.constraint;

import ru.text.nastya.validation.validator.PageSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {PageSizeValidator.class})
@Target(PARAMETER)
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface PageSize {

    String message() default "{blog.validation.method.page.size}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int maxSize() default 100;
}
