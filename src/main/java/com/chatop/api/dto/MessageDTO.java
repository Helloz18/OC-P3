package com.chatop.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageDTO {

    private String message;

    @JsonProperty("user_id")
    private int userId;

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
