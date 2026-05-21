package com.rental_hubs_app.controller;

import java.io.IOException;

import com.rental_hubs_app.dao.ClothDAO;
import com.rental_hubs_app.dao.RentalDAO;
import com.rental_hubs_app.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/admin-dashboard")
public class AdminDashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDAO userDAO = new UserDAO();
    private ClothDAO clothDAO = new ClothDAO();
    private RentalDAO rentalDAO = new RentalDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("totalUsers", userDAO.getTotalUsers());
        request.setAttribute("totalClothes", clothDAO.getTotalClothes());
        request.setAttribute("activeRentals", rentalDAO.getActiveRentalCount());
        request.setAttribute("monthlyRevenue", rentalDAO.getTotalRevenue());

        request.getRequestDispatcher("/WEB-INF/pages/admin_dashboard.jsp")
                .forward(request, response);
    }
}