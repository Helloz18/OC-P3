package com.chatop.api.dto;

import com.chatop.api.model.User;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    public static UserDTO toUserDTO(final User user) {
        return new UserDTO(user.getEmail(), user.getName(), user.getPassword());
    }
}
