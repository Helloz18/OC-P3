package com.chatop.api.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Timestamp;

/**
 * Entity User needs three NOT NULL fields to be created: email, name, password
 * This entity has two fields to follow creation: createdAt and modification: updatedAt of a user
 * These fields are stored in Timestamp in DB as YYYY-MM-DD HH:mm:ss
 * BUT openApi awaits 'YYYY-MM-DDTHH:mm:ssZ'
 */

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "email", nullable = false)
    private String email;

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
