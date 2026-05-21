package com.rental_hubs_app.controller;

import java.io.IOException;
import java.util.List;

import com.rental_hubs_app.dao.RentalDAO;
import com.rental_hubs_app.model.RentalModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin-rentals")
public class AdminRentalsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RentalDAO rentalDAO = new RentalDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String search = request.getParameter("search");

        List<RentalModel> rentals;

        if (search != null && !search.trim().isEmpty()) {
            rentals = rentalDAO.searchRentals(search);
        } else {
            rentals = rentalDAO.getAllRentals();
        }

        request.setAttribute("rentals", rentals);
        request.setAttribute("search", search);

        request.getRequestDispatcher("/WEB-INF/pages/admin_rentals.jsp")
                .forward(request, response);
    }
}