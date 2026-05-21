package com.rental_hubs_app.controller;

import java.io.IOException;

import com.rental_hub_app.model.ClothModel;
import com.rental_hub_app.service.ClothService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/rent-page")
public class RentPageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ClothService clothService = new ClothService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int clothId = Integer.parseInt(request.getParameter("id"));

        ClothModel cloth = clothService.getClothById(clothId);

        request.setAttribute("cloth", cloth);
        request.getRequestDispatcher("/WEB-INF/pages/rent_page.jsp").forward(request, response);
    }
}