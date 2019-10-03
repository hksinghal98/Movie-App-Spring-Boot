package com.stackroute.MovieServiceApplication.customException;

public class MovieAlreadyExistException extends Exception{
    private String message;
    public MovieAlreadyExistException(String message) {
        super(message);
        this.message=message;
    }
}
