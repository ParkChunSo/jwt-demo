package com.example.jwt_example.error;

public class InvalidJwtAuthenticationException extends RuntimeException {
    public InvalidJwtAuthenticationException(String message) {
        System.out.println(message);
    }
}
