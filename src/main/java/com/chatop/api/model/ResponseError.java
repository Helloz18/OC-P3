package com.chatop.api.model;

public class ResponseError {

    public final String message;

    public String getMessage() {
        return message;
    }

    public ResponseError(final String message) {
        this.message = message;
    }
}
