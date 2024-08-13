package com.contactapp.exception;

public class UserIsPresentException extends RuntimeException {
    public UserIsPresentException(String message) {
        super(message);
    }
}
