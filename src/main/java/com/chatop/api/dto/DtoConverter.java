package com.chatop.api.dto;

import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    public static UserDTO toUserDTO(final User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt().toString());
        if(user.getUpdatedAt() != null) {
            userDTO.setUpdatedAt(user.getUpdatedAt().toString());
        }
        return new UserDTO(user.getEmail(), user.getName(), user.getPassword());
    }

    public static RentalDTO toRentalDTO(final Rental rental) {
        RentalDTO rentalDTO = new RentalDTO();
        rentalDTO.setId(rental.getId());
        rentalDTO.setName(rental.getName());
        rentalDTO.setSurface(rental.getSurface());
        rentalDTO.setPrice(rental.getPrice());
        rentalDTO.setPicture(rental.getPicture());
        rentalDTO.setDescription(rental.getDescription());
        rentalDTO.setOwnerId(rental.getUser().getId());
        rentalDTO.setCreatedAt(rental.getCreatedAt().toString());
        if(rental.getUpdatedAt() != null) {
            rentalDTO.setUpdatedAt(rental.getUpdatedAt().toString());
        }
        return rentalDTO;
    }
}
