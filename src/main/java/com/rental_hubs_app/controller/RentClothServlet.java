package com.rental_hubs_app.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.rental_hub_app.model.ClothModel;
import com.rental_hub_app.model.RentalModel;
import com.rental_hub_app.model.UserModel;
import com.rental_hub_app.service.ClothService;
import com.rental_hub_app.service.RentalService;
import com.rental_hub_app.utils.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/rent-cloth")
public class RentClothServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private RentalService rentalService = new RentalService();
    private ClothService clothService = new ClothService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserModel loggedUser = (UserModel) SessionUtil.getAttribute(request, "loggedUser");

        if (loggedUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        int clothId = Integer.parseInt(request.getParameter("cloth_id"));

        LocalDate rentalLocalDate = LocalDate.parse(request.getParameter("rental_date"));
        LocalDate returnLocalDate = LocalDate.parse(request.getParameter("return_date"));

        long days = ChronoUnit.DAYS.between(rentalLocalDate, returnLocalDate);

        if (days <= 0) {
            request.setAttribute("error", "Return date must be after rental date");
            response.sendRedirect(request.getContextPath() + "/browse-clothes");
            return;
        }

        ClothModel cloth = clothService.getClothById(clothId);

        double totalPrice = cloth.getRentalPrice() * days;

        RentalModel rental = new RentalModel();
        rental.setUserId(loggedUser.getUserId());
        rental.setClothId(clothId);
        rental.setRentalDate(Date.valueOf(rentalLocalDate));
        rental.setReturnDate(Date.valueOf(returnLocalDate));
        rental.setTotalPrice(totalPrice);
        rental.setStatus("pending");

        String result = rentalService.requestRental(rental);

        if ("success".equals(result)) {
            response.sendRedirect(request.getContextPath() + "/my-rentals");
        } else {
            response.sendRedirect(request.getContextPath() + "/browse-clothes");
        }
    }
}