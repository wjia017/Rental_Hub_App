package com.rental_hubs_app.controller;

import java.io.IOException;
import java.util.List;

import com.rental_hub_app.dao.ClothDAO;
import com.rental_hub_app.model.ClothModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/clothes")
public class ClothServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ClothDAO clothDAO = new ClothDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<ClothModel> clothes = clothDAO.getAllClothes();

        request.setAttribute("clothes", clothes);

        request.getRequestDispatcher("/WEB-INF/pages/clothes.jsp")
                .forward(request, response);
    }
}