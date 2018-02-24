package ru.text.nastya.web.controllers.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.text.nastya.exception.DataNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@ControllerAdvice(basePackages = {"ru.text.nastya.web.controllers"})
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    private static final int MAX_STACKTRACE_DEPTH = 10;

    private String gatherStackTrace(Throwable ex) {
        String stackTrace = null;
        if (ex.getStackTrace() != null && ex.getStackTrace().length > 0) {
            StackTraceElement[] stackTraceElements = ex.getStackTrace();
            int toIndex = Math.min(MAX_STACKTRACE_DEPTH, stackTraceElements.length);
            StackTraceElement[] stackTracePart = Arrays.copyOfRange(stackTraceElements, 0, toIndex);
            stackTrace = Arrays.toString(stackTracePart);
        }
        return stackTrace;
    }

    private String gatherFullCauseMessage(Throwable ex) {
        StringBuilder fullMessage = StringUtils.isEmpty(ex.getMessage())
                ? new StringBuilder()
                : new StringBuilder(ex.getMessage());
        Throwable cause = ex.getCause();
        while (cause != null) {
            if (!StringUtils.isEmpty(cause.getMessage())) {
                fullMessage.append(System.lineSeparator())
                        .append("->")
                        .append(cause.getMessage());
            }
            cause = cause.getCause();
        }
        return fullMessage.toString();
    }

    private GeneralExceptionResponse buildGeneralExceptionResponse(HttpServletRequest req, Throwable ex) {
        GeneralExceptionResponse response = new GeneralExceptionResponse();
        response.setExceptionMessage(gatherFullCauseMessage(ex));
        response.setStackTrace(gatherStackTrace(ex));
        response.setExceptionName(ex.getClass().getCanonicalName());
        response.setRequestUrl(req.getRequestURL().toString());
        response.setMethod(req.getMethod());
        logger.error("Error when process request {}", response, ex);
        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public GeneralExceptionResponse handleOtherException(HttpServletRequest req, Exception ex) {
        return buildGeneralExceptionResponse(req, ex);
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GeneralExceptionResponse handleNotFoundException(HttpServletRequest req, DataNotFoundException ex) {
        return buildGeneralExceptionResponse(req, ex);
    }
}
