package com.stackroute.MovieServiceApplication.customException;

import org.springframework.http.HttpStatus;

public class MovieNotFoundException extends Exception {
    private String message;
    public MovieNotFoundException(String message) {
        super(message);
        this.message=message;
    }
}
