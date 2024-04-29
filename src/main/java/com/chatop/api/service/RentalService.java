package com.chatop.api.service;

import com.chatop.api.dto.ModelConverter;
import com.chatop.api.dto.RentalDTO;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;
import com.chatop.api.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService implements IRentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental getRentalById(int id) throws Exception {
        return rentalRepository.findById(id).orElseThrow(() ->
                new Exception("No rental registered with this id : " + id));
    }

    @Override
    public Rental createRental(RentalDTO rentalDTO) {
        Rental rental = ModelConverter.toRentalCreate(rentalDTO);
        String token = jwtService.getToken();
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(username).orElseThrow();
        rental.setUser(user);

        return rentalRepository.save(rental);
    }

    @Override
    public Rental updateRental(int id, RentalDTO rentalDTO) {
        Rental rental = ModelConverter.toRentalUpdate(rentalDTO);
        return rental;

    }
}
