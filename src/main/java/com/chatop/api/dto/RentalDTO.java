package com.chatop.api.dto;

import com.chatop.api.utils.TimestampAdapter;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.sql.Timestamp;

public class RentalDTO {

    @Schema(
            description = "Id of the rental",
            name = "id",
            type = "int",
            example = "10")
    private int id;

    @Schema(
            description = "name of the rental",
            name = "name",
            type = "string",
            example = "My rental")
    private String name;

    @Schema(
            description = "surface of the rental in m2",
            name = "surface",
            type = "int",
            example = "80")
    private int surface;

    @Schema(
            description = "price of the rental in €",
            name = "price",
            type = "long",
            example = "500")
    private long price;

    @Schema(
            description = "path to the picture of a rental",
            name = "picture",
            type = "string",
            example = "http://my-images.com/123.jpg")
    private String picture;

    @Schema(
            description = "description of the rental",
            name = "description",
            type = "string",
            example = "My rental is the best.")
    private String description;

    @Schema(
            description = "id of the owner of the rental",
            name = "owner_id",
            type = "int",
            example = "5")
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
        this.createdAt = TimestampAdapter.convertTimestampFromOpenApi(createdAt);
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = TimestampAdapter.convertTimestampFromOpenApi(updatedAt);

    }
}
