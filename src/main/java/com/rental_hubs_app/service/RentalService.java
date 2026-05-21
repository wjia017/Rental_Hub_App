package com.rental_hubs_app.service;

import java.sql.Date;
import java.util.List;

import com.rental_hubs_app.dao.RentalDAO;
import com.rental_hubs_app.model.RentalModel;

public class RentalService {

    private RentalDAO rentalDAO = new RentalDAO();

    public String requestRental(RentalModel rental) {

        if (rental.getUserId() <= 0) {
            return "User is not valid";
        }

        if (rental.getClothId() <= 0) {
            return "Cloth is not valid";
        }

        Date rentalDate = rental.getRentalDate();
        Date returnDate = rental.getReturnDate();

        if (rentalDate == null || returnDate == null) {
            return "Rental date and return date are required";
        }

        if (returnDate.before(rentalDate)) {
            return "Return date cannot be before rental date";
        }

        boolean result = rentalDAO.requestRental(rental);

        if (result) {
            return "success";
        }

        return "Unable to send rental request";
    }

    public List<RentalModel> getRentalsByUser(int userId) {
        return rentalDAO.getRentalsByUser(userId);
    }

    public List<RentalModel> getAllRentals() {
        return rentalDAO.getAllRentals();
    }

    public boolean updateRentalStatus(int rentalId, String status) {
        return rentalDAO.updateRentalStatus(rentalId, status);
    }
}