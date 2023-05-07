package com.example.javaclasses.Errors;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String message) {
        super("Element not found: " + message);
    }
}
