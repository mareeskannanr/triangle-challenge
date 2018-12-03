package com.app.advice;

import com.app.exception.AppException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * This method handles exception from triangle detail validation
     * @param exception
     * @param headers
     * @param status
     * @param request
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                       HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseEntity.badRequest().body(errorList);
    }

    /***
     * This method handles logical exception when determine triangle type
     * @param appException
     * @return ResponseEntity
     */
    @ExceptionHandler(AppException.class)
    public ResponseEntity handleAppException(AppException appException) {
        return ResponseEntity.status(appException.getStatusCode()).body(appException.getErrorList());
    }

}
