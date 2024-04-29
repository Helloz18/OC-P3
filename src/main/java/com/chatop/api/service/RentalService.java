package com.chatop.api.service;

import com.chatop.api.model.Rental;
import com.chatop.api.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService implements IRentalService {

    @Autowired
    private RentalRepository rentalRepository;

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
    public Rental createRental(Rental rental) {
        return rentalRepository.save(rental);
    }
}
