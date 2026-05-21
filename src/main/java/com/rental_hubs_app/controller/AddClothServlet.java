package com.rental_hubs_app.controller;

import java.io.File;
import java.io.IOException;

import com.rental_hubs_app.model.ClothModel;
import com.rental_hubs_app.service.ClothService;
import com.rental_hubs_app.utils.FileUploadUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/add-cloth")
@MultipartConfig
public class AddClothServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR =
            System.getProperty("user.home") + File.separator + "rental_hub_clothes";

    private ClothService clothService = new ClothService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/add_cloth.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String clothName = request.getParameter("cloth_name");
        String category = request.getParameter("category");
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        double rentalPrice = Double.parseDouble(request.getParameter("rental_price"));

        String imageName = null;
        Part imagePart = request.getPart("image_name");

        if (imagePart != null && imagePart.getSize() > 0) {
            String extension = FileUploadUtil.getFileExtension(imagePart.getSubmittedFileName());
            imageName = System.currentTimeMillis() + extension;
            FileUploadUtil.saveFile(imagePart, UPLOAD_DIR, imageName);
        }

        ClothModel cloth = new ClothModel(
                clothName,
                category,
                size,
                color,
                rentalPrice,
                description,
                imageName,
                status
        );

        String result = clothService.addCloth(cloth);

        if ("success".equals(result)) {
            response.sendRedirect(request.getContextPath() + "/clothes");
        } else {
            request.setAttribute("error", result);
            request.setAttribute("cloth", cloth);
            request.getRequestDispatcher("/WEB-INF/pages/add_cloth.jsp").forward(request, response);
        }
    }
}