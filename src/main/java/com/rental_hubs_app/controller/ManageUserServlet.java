package com.rental_hubs_app.controller;

import java.io.IOException;
import java.util.List;

import com.rental_hubs_app.dao.UserDAO;
import com.rental_hubs_app.model.UserModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/manage-users")
public class ManageUserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String search = request.getParameter("search");

        List<UserModel> users;

        if (search != null && !search.trim().isEmpty()) {
            users = userDAO.searchUsers(search);
        } else {
            users = userDAO.getAllUsers();
        }

        request.setAttribute("users", users);
        request.setAttribute("search", search);

        request.getRequestDispatcher("/WEB-INF/pages/manage_users.jsp")
                .forward(request, response);
    }
}