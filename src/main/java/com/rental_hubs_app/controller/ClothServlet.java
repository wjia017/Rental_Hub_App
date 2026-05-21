package com.rental_hubs_app.controller;

import java.io.IOException;
import java.util.List;

import com.rental_hubs_app.dao.ClothDAO;
import com.rental_hubs_app.model.ClothModel;

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

        String search = request.getParameter("search");

        List<ClothModel> clothes;

        if (search != null && !search.trim().isEmpty()) {
            clothes = clothDAO.searchClothes(search);
        } else {
            clothes = clothDAO.getAllClothes();
        }

        request.setAttribute("clothes", clothes);
        request.setAttribute("search", search);

        request.getRequestDispatcher("/WEB-INF/pages/clothes.jsp")
                .forward(request, response);
    }
}