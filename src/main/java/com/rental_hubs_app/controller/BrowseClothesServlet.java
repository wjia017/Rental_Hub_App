package com.rental_hubs_app.controller;

import java.io.IOException;
import java.util.List;

import com.rental_hub_app.model.ClothModel;
import com.rental_hub_app.service.ClothService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/browse-clothes")
public class BrowseClothesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ClothService clothService = new ClothService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<ClothModel> clothes = clothService.getAllClothes();

        request.setAttribute("clothes", clothes);
        request.getRequestDispatcher("/WEB-INF/pages/browse_clothes.jsp").forward(request, response);
    }
}