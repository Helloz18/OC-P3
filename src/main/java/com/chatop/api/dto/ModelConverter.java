package com.chatop.api.dto;

import com.chatop.api.model.Message;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class ModelConverter {

    private static UserRepository userRepository;

    private static RentalRepository rentalRepository;

    public ModelConverter(UserRepository userRepository, RentalRepository rentalRepository) {
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

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

    public static Message toMessageCreate(final MessageDTO messageDTO) {
        User user = userRepository.findById(messageDTO.getUserId()).orElseThrow();
        Rental rental = rentalRepository.findById(messageDTO.getRentalId()).orElseThrow();
        Message message = new Message(
                messageDTO.getMessage(), user, rental);
        message.setCreatedAt(Instant.now().toString());
        return message;
    }
}
