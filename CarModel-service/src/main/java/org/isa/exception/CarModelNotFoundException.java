package org.isa.exception;

public class CarModelNotFoundException extends RuntimeException {
    public CarModelNotFoundException(String message) {
        super(message);
    }
}
