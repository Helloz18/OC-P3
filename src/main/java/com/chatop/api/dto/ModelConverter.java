package com.chatop.api.dto;

import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;
import com.chatop.api.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ModelConverter {

    @Autowired
    private static JwtService jwtService;

    @Autowired
    private static UserRepository userRepository;

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

    public static Rental toRentalCreate(final RentalDTO rentalDTO) {
        Rental rental =
                new Rental(rentalDTO.getName(),
                           rentalDTO.getSurface(),
                           rentalDTO.getPrice(),
                           rentalDTO.getPicture(),
                           rentalDTO.getDescription());
        rental.setCreatedAt(Instant.now().toString());
        return rental;
    }

    public static Rental toRentalUpdate(final RentalDTO rentalDTO) {
        Rental rental =
                new Rental(rentalDTO.getName(),
                           rentalDTO.getSurface(),
                           rentalDTO.getPrice(),
                           rentalDTO.getDescription());
        return rental;
    }
}
