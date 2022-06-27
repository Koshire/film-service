package com.andersenlab.backbasetesttask.config.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public class ExceptionHelper {

    public static ResponseEntity<Object> processException(ErrorModel ex, HttpStatus status) {
        log.warn(ex.getMessage(), ex);
        return new ResponseEntity<>(ex, status);
    }
}
