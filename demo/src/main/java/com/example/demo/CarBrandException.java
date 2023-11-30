package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CarBrandException extends RuntimeException {
    private final String errorMessage;

    public CarBrandException(String message) {
        this.errorMessage = message;
    }
}
