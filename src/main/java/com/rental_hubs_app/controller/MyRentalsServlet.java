package com.rental_hubs_app.controller;

import java.io.IOException;
import java.util.List;

import com.rental_hub_app.model.RentalModel;
import com.rental_hub_app.model.UserModel;
import com.rental_hub_app.service.RentalService;
import com.rental_hub_app.utils.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/my-rentals")
public class MyRentalsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RentalService rentalService = new RentalService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserModel loggedUser = (UserModel) SessionUtil.getAttribute(request, "loggedUser");

        List<RentalModel> rentals = rentalService.getRentalsByUser(loggedUser.getUserId());

        request.setAttribute("rentals", rentals);
        request.getRequestDispatcher("/WEB-INF/pages/my_rentals.jsp").forward(request, response);
    }
}