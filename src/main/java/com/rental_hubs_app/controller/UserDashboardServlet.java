package com.rental_hubs_app.controller;

import java.io.IOException;
import java.util.List;

import com.rental_hub_app.dao.RentalDAO;
import com.rental_hub_app.model.ClothModel;
import com.rental_hub_app.model.UserModel;
import com.rental_hub_app.service.ClothService;
import com.rental_hub_app.utils.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/user-dashboard")
public class UserDashboardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ClothService clothService = new ClothService();
    private RentalDAO rentalDAO = new RentalDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserModel loggedUser = (UserModel) SessionUtil.getAttribute(request, "loggedUser");

        if (loggedUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        List<ClothModel> clothes = clothService.getAllClothes();

        request.setAttribute("clothes", clothes);
        request.setAttribute("activeRentals",
                rentalDAO.getActiveRentalCountByUser(loggedUser.getUserId()));
        request.setAttribute("totalSpent",
                rentalDAO.getTotalSpentByUser(loggedUser.getUserId()));
        request.setAttribute("pendingRentals",
                rentalDAO.getPendingRentalCountByUser(loggedUser.getUserId()));

        request.getRequestDispatcher("/WEB-INF/pages/user_dashboard.jsp")
                .forward(request, response);
    }
}