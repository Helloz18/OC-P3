package com.chatop.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class UserDTO {

    private int id;

    private String email;

    private String name;

    private String password;

    @JsonProperty("created_at")
    private Timestamp createdAt;

    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    public UserDTO() {}

    public UserDTO(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id =
                id;
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
