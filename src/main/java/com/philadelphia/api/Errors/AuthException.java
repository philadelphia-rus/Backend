package com.philadelphia.api.Errors;

public class AuthException extends RuntimeException{

    public AuthException(String message) {
        super(message);
    }
}