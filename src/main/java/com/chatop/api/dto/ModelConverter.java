package com.chatop.api.dto;

import com.chatop.api.model.User;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ModelConverter {

    public static User toUserCreate(final UserDTO userDTO) {
        User user = new User(userDTO.getEmail(), userDTO.getName(), userDTO.getPassword());
        user.setCreatedAt(Instant.now().toString());
        return user;
    }

    public static User toUserUpdate(final UserDTO userDTO) {
        User user = new User(userDTO.getEmail(), userDTO.getName(), userDTO.getPassword());
        user.setUpdatedAt(Instant.now().toString());
        return user;
    }
}
