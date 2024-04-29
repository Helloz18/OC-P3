package com.chatop.api.model;

import com.chatop.api.dto.RentalDTO;

import java.util.List;

public class Rentals {

    private List<RentalDTO> rentals;

    public Rentals(List<RentalDTO> rentals) {
        this.rentals = rentals;
    }

    public List<RentalDTO> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalDTO> rentals) {
        this.rentals = rentals;
    }
}
