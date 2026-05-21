package com.rental_hubs_app.controller;

import java.io.IOException;

import com.rental_hubs_app.service.RentalService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update-rental-status")
public class UpdateRentalStatusServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RentalService rentalService = new RentalService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int rentalId = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");

        rentalService.updateRentalStatus(rentalId, status);

        response.sendRedirect(request.getContextPath() + "/admin-rentals");
    }
}