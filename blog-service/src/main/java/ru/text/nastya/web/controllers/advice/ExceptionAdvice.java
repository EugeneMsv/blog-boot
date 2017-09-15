package ru.text.nastya.web.controllers.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.text.nastya.exception.DataNotFoundException;

@ControllerAdvice(basePackages = {"ru.text.nastya.web.controllers"})
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String handleControllerException(Exception ex) {
        return getGeneralResponse(ex);
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleNotFoundException(DataNotFoundException ex) {
        return getGeneralResponse(ex);
    }

    private String getGeneralResponse(Exception e) {
        logger.error("Server exception", e);
        return e.getMessage();
    }
}
