package com.chatop.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class MessageDTO {

    @Schema(
            description = "message send to the owner of the rental",
            name = "message",
            type = "string",
            example = "I want to know more about this rental.")
    private String message;

    @Schema(
            description = "id of the user who sends the message.",
            name = "user_id",
            type = "int",
            example = "5")
    @JsonProperty("user_id")
    private int userId;

    @Schema(
            description = "id of the rental.",
            name = "rental_id",
            type = "int",
            example = "10")
    @JsonProperty("rental_id")
    private int rentalId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message =
                message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId =
                userId;
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId =
                rentalId;
    }
}
