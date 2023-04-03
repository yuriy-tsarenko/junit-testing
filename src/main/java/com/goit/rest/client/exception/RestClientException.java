package com.goit.rest.client.exception;

public class RestClientException extends RuntimeException {
    public RestClientException() {
    }

    public RestClientException(String message) {
        super(message);
    }
}
