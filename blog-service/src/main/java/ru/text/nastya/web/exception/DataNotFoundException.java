package ru.text.nastya.web.exception;

import org.springframework.http.HttpStatus;

@Deprecated
public class DataNotFoundException extends WebControllerException {

    public DataNotFoundException(HttpStatus httpStatus, String message) {
        super(httpStatus, message);
    }

}
