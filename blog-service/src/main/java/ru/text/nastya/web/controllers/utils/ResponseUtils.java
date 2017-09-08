package ru.text.nastya.web.controllers.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Utils for building response from web controllers
 */
public class ResponseUtils {


    private static final ResponseUtils INSTANCE = new ResponseUtils();

    public static ResponseUtils getInstance() {
        return INSTANCE;
    }

    private final HttpHeaders noCacheHeaders = new HttpHeaders();

    {
        noCacheHeaders.setPragma("no-cache");
        noCacheHeaders.setCacheControl("no-cache, must-revalidate");
        noCacheHeaders.setExpires(0);
    }

    /**
     * Response status OK with no cache
     *
     * @param body content body
     * @param <T>  body type
     * @return built response entity
     */
    public <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(noCacheHeaders)
                .body(body);
    }

    /**
     * Response status OK with no cache
     *
     * @return built response entity
     */
    public ResponseEntity<?> ok() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(noCacheHeaders).build();
    }

    /**
     * Response status CREATED with no cache
     *
     * @param body content body
     * @param <T>  body type
     * @return built response entity
     */
    public <T> ResponseEntity<T> created(T body) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(noCacheHeaders)
                .body(body);
    }

    public <T> ResponseEntity<T> noContent() {
        return ResponseEntity
                .noContent()
                .build();
    }

    public <T> ResponseEntity<T> badRequest(T body) {
        return ResponseEntity
                .badRequest()
                .body(body);
    }

    public <T> ResponseEntity<T> unauthorized(T body) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(body);
    }

    public <T> ResponseEntity<T> forbidden(T body) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(body);
    }

    public <T> ResponseEntity<T> notSupportedMethod() {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .build();
    }

    public <T> ResponseEntity<T> notFound() {
        return ResponseEntity
                .notFound()
                .build();
    }

    public ResponseEntity<String> internalError(String body) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(body);
    }

    public <T> ResponseEntity<T> accepted() {
        return ResponseEntity
                .accepted()
                .build();
    }
}
