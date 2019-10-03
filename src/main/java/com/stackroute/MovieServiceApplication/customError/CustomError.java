package com.stackroute.MovieServiceApplication.customError;

import org.springframework.http.HttpStatus;

public class CustomError {
    private HttpStatus httpStatus;
    private String error;
    public CustomError(HttpStatus httpStatus, String error) {
        this.httpStatus = httpStatus;
        this.error = error;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
