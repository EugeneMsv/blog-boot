package ru.text.nastya.web.controllers.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.text.nastya.exception.DataNotFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = {"ru.text.nastya.web.controllers"})
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    private GeneralExceptionResponse getGeneralResponse(HttpServletRequest req, Exception ex) {
        GeneralExceptionResponse response = new GeneralExceptionResponse();
        response.setExceptionMessage(ex.getMessage());
        response.setRequestUrl(req.getRequestURL().toString());
        response.setMethod(req.getMethod());
        logger.error("Error when process request {}", response, ex);

        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public GeneralExceptionResponse handleOtherException(HttpServletRequest req, Exception ex) {
        return getGeneralResponse(req, ex);
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GeneralExceptionResponse handleNotFoundException(HttpServletRequest req, DataNotFoundException ex) {
        return getGeneralResponse(req, ex);
    }
}
