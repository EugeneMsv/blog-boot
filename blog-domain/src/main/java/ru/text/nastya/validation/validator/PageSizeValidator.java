package ru.text.nastya.validation.validator;

import org.springframework.data.domain.Pageable;
import ru.text.nastya.validation.constraint.PageSize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Optional.ofNullable;

public class PageSizeValidator implements ConstraintValidator<PageSize, Pageable> {

    private int maxSize;

    @Override
    public void initialize(PageSize constraintAnnotation) {
        this.maxSize = constraintAnnotation.maxSize();
    }

    @Override
    public boolean isValid(Pageable value, ConstraintValidatorContext context) {
        return ofNullable(value)
                .map(v -> v.getPageSize() <= maxSize)
                .orElse(true);
    }
}
