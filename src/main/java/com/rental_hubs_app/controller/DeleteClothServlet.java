package com.rental_hubs_app.controller;

import java.io.IOException;

import com.rental_hubs_app.service.ClothService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete-cloth")
public class DeleteClothServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ClothService clothService = new ClothService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int clothId = Integer.parseInt(request.getParameter("id"));

        clothService.deleteCloth(clothId);

        response.sendRedirect(request.getContextPath() + "/clothes");
    }
}