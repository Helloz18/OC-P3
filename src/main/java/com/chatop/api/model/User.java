package com.chatop.api.model;
import com.chatop.api.utils.TimestampAdapter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.sql.Timestamp;

/**
 * Entity User needs three NOT NULL fields to be created: email, name, password
 */

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

    @Schema(
            description = "Id of the user",
            name = "id",
            type = "int",
            example = "5")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Schema(
            description = "email of the user",
            name = "email",
            type = "string",
            example = "test@test.com")
    @Column(name = "email", nullable = false)
    private String email;

    @Schema(
            description = "name of the user",
            name = "name",
            type = "string",
            example = "test TEST")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name="created_at", columnDefinition = "TIMESTAMP")
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @Column(name="updated_at", columnDefinition = "TIMESTAMP")
    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    public User() {
    }

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
