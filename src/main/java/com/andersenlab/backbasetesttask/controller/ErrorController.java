package com.andersenlab.backbasetesttask.controller;

import com.andersenlab.backbasetesttask.config.error.ErrorModel;
import com.andersenlab.backbasetesttask.config.error.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static com.andersenlab.backbasetesttask.config.error.ExceptionHelper.processException;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String message = Optional.of(ex)
                .map(BindException::getFieldError)
                .map(FieldError::getDefaultMessage)
                .orElse(ex.getLocalizedMessage());
        String field = Optional.of(ex)
                .map(BindException::getFieldError)
                .map(FieldError::getField)
                .orElse(null);
        return processException(new ErrorModel(null, message, field), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<Object> handleException(NotFoundException ex) {
        return processException(new ErrorModel(ex.getLocalizedMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<Object> handleException(ConstraintViolationException ex) {
        return processException(new ErrorModel(ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleException(Exception ex) {
        return processException(new ErrorModel(ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }
}
