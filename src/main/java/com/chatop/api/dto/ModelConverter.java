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

    public static Rental toRentalUpdate(final Rental rental, final UpdateRentalDTO updateRentalDTO) {
        if(!rental.getName().equals(updateRentalDTO.getName())) {
            rental.setName(updateRentalDTO.getName());
        }
        if(rental.getSurface() != updateRentalDTO.getSurface()) {
            rental.setSurface(updateRentalDTO.getSurface());
        }
        if(rental.getPrice() != updateRentalDTO.getPrice()) {
            rental.setPrice(updateRentalDTO.getPrice());
        }
        if(!rental.getDescription().equals(updateRentalDTO.getDescription())) {
            rental.setDescription(updateRentalDTO.getDescription());
        }
        rental.setUpdatedAt(Instant.now().toString());
        return rental;
    }
}
