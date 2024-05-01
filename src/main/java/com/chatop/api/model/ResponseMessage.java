package com.chatop.api.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class ResponseMessage {

    @Schema(
            description = "Message returned by some Http requests.",
            name = "message",
            type = "String",
            example = "Rental created !")
    public final String message;

    public String getMessage() {
        return message;
    }

    public ResponseMessage(final String message) {
        this.message = message;
    }
}
