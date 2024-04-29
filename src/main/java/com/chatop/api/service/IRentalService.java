package com.chatop.api.service;

import com.chatop.api.dto.RentalDTO;
import com.chatop.api.model.Rental;

import java.util.List;
import java.util.Optional;

public interface IRentalService {

    List<Rental> getAllRentals();

    Rental getRentalById(int id) throws Exception;

    Rental createRental(RentalDTO rentalDTO);

    Rental updateRental(int id, RentalDTO rentalDTO);

}
