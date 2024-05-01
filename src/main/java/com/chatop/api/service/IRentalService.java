package com.chatop.api.service;

import com.chatop.api.dto.RentalDTO;
import com.chatop.api.dto.UpdateRentalDTO;
import com.chatop.api.model.Rental;
import com.chatop.api.model.Rentals;

public interface IRentalService {

    Rentals getAllRentals();

    RentalDTO getRentalById(int id) throws Exception;

    Rental createRental(RentalDTO rentalDTO) throws Exception;

    Rental updateRental(int id, UpdateRentalDTO updateRentalDTO) throws Exception;

}
