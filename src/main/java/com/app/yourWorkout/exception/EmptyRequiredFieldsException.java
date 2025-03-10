package com.app.yourWorkout.exception;

public class EmptyRequiredFieldsException extends RuntimeException {
    public EmptyRequiredFieldsException(String message) {
        super(message);
    }
}
