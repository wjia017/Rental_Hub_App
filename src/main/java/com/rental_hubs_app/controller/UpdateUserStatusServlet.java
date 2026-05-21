package com.rental_hubs_app.controller;

import java.io.IOException;

import com.rental_hubs_app.dao.UserDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update-user-status")
public class UpdateUserStatusServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");

        userDAO.updateUserStatus(userId, status);

        response.sendRedirect(request.getContextPath() + "/manage-users");
    }
}