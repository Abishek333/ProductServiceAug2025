package org.example.producctserviceaug2025.Exceptions;

public class UnauthorizedAccessException extends RuntimeException{
    public UnauthorizedAccessException(String message) {
        super(message);
    }

//    @Override
//    public String getMessage() {
//        return super.getMessage();
//    }
}
