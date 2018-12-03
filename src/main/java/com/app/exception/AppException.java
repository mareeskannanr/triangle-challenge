package com.app.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class AppException extends RuntimeException {

    private HttpStatus statusCode;
    private List<String> errorList;

    /**
     * This Method create exception with HttpStatus code with error information
     * @param statusCode
     * @param errorList
     */
    public AppException(HttpStatus statusCode, List<String> errorList) {
        this.statusCode = statusCode;
        this.errorList = errorList;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public List<String> getErrorList() {
        return errorList;
    }

}