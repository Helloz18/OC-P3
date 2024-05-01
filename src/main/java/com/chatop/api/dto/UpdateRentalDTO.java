package com.chatop.api.dto;

import com.chatop.api.utils.TimestampAdapter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class UpdateRentalDTO {

    private String name;

    private int surface;

    private long price;

    private String description;

    @JsonIgnore
    @JsonProperty("updated_at")
    private Timestamp updatedAt;


    public UpdateRentalDTO(String name, int surface, long price, String description) {
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    //Timestamp in db will be stored as yyyy-MM-dd HH:mm:ss

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = TimestampAdapter.convertTimestampFromOpenApi(updatedAt);

    }
}
