package com.contactapp.exception;

public class UserDetailsEmpty extends RuntimeException {
    public UserDetailsEmpty(String message) {
        super(message);
    }
}
