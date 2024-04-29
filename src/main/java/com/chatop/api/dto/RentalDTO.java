package com.chatop.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class RentalDTO {

    private int id;
    private String name;

    private int surface;

    private long price;

    private String picture;

    private String description;

    @JsonProperty("owner_id")
    private int ownerId;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    public RentalDTO() {}

    public RentalDTO(String name, int surface, long price, String description) {
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.description = description;
    }

    public RentalDTO(String name, int surface, long price, String picture, String description) {
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.picture = picture;
        this.description = description;
    }

    public RentalDTO(String name, int surface, long price, String picture, String description, int ownerId) {
        this.name = name;
        this.surface = surface;
        this.price = price;
        this.picture = picture;
        this.description = description;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id =
                id;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    //Timestamp in db will be stored as yyyy-MM-dd HH:mm:ss
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        String createdAtConvert = this.convertTimestampFromOpenApi(createdAt);
        this.createdAt = Timestamp.valueOf(createdAtConvert);
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        String updatedAtConvert = this.convertTimestampFromOpenApi(updatedAt);
        this.updatedAt = Timestamp.valueOf(updatedAtConvert);

    }

    private String convertTimestampFromOpenApi(String openApiTimestamp) {
        String convert = openApiTimestamp.replace('T', ' ');
        convert = convert.replace('Z', ' ');
        return convert;
    }
}
