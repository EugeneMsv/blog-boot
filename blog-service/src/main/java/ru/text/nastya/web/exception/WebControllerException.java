package ru.text.nastya.web.exception;

import org.springframework.http.HttpStatus;

@Deprecated
public abstract class WebControllerException extends RuntimeException {

    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    WebControllerException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
