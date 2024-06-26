package com.chatop.api.service;

import com.chatop.api.dto.DtoConverter;
import com.chatop.api.dto.ModelConverter;
import com.chatop.api.dto.RentalDTO;
import com.chatop.api.dto.UpdateRentalDTO;
import com.chatop.api.model.Rental;
import com.chatop.api.model.Rentals;
import com.chatop.api.model.User;
import com.chatop.api.repository.RentalRepository;
import com.chatop.api.repository.UserRepository;
import com.chatop.api.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalService implements IRentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Rentals getAllRentals() {
        List<Rental> rentalList = rentalRepository.findAll();
        List<RentalDTO> rentalDtoList = new ArrayList<RentalDTO>();
        rentalList.forEach(rental -> rentalDtoList.add(DtoConverter.toRentalDTO(rental)));
        return new Rentals(rentalDtoList);
    }

    @Override
    public RentalDTO getRentalById(int id) throws Exception {
        Rental rental = rentalRepository.findById(id).orElseThrow(() ->
                new Exception("No rental registered with this id : " + id));
        return DtoConverter.toRentalDTO(rental);
    }

    @Override
    public Rental createRental(RentalDTO rentalDTO) throws Exception {
        Rental rental = ModelConverter.toRentalCreate(rentalDTO);
        String token = jwtService.getToken();
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByEmail(username).orElseThrow(
                () -> new Exception("No user found with this email : " + username));
        rental.setUser(user);

        return rentalRepository.save(rental);
    }

    @Override
    public Rental updateRental(int id, UpdateRentalDTO updateRentalDTO) throws Exception {
        Rental rental = rentalRepository.findById(id).orElseThrow(
                () -> new Exception("No rental registered with this id : " + id));
        return rentalRepository.save(ModelConverter.toRentalUpdate(rental, updateRentalDTO));
    }
}
