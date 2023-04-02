package com.philadelphia.api.Errors;

public class Failed extends RuntimeException{

    public Failed(String message) {
        super(message);
    }
}
