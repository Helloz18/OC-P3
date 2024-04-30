package com.chatop.api.service;

import com.chatop.api.dto.RentalDTO;
import com.chatop.api.dto.UpdateRentalDTO;
import com.chatop.api.model.Rental;
import com.chatop.api.model.Rentals;

import java.util.List;
import java.util.Optional;

public interface IRentalService {

    Rentals getAllRentals();

    RentalDTO getRentalById(int id) throws Exception;

    Rental createRental(RentalDTO rentalDTO);

    Rental updateRental(int id, UpdateRentalDTO updateRentalDTO);

}
