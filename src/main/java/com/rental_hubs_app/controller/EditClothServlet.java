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

@WebServlet("/edit-cloth")
@MultipartConfig
public class EditClothServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR =
            System.getProperty("user.home") + File.separator + "rental_hub_clothes";

    private ClothService clothService = new ClothService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int clothId = Integer.parseInt(request.getParameter("id"));

        ClothModel cloth = clothService.getClothById(clothId);

        request.setAttribute("cloth", cloth);
        request.getRequestDispatcher("/WEB-INF/pages/edit_cloth.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int clothId = Integer.parseInt(request.getParameter("cloth_id"));

        String clothName = request.getParameter("cloth_name");
        String category = request.getParameter("category");
        String size = request.getParameter("size");
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        double rentalPrice = Double.parseDouble(request.getParameter("rental_price"));

        String imageName = request.getParameter("old_image_name");

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

        cloth.setClothId(clothId);

        boolean result = clothService.updateCloth(cloth);

        if (result) {
            response.sendRedirect(request.getContextPath() + "/clothes");
        } else {
            request.setAttribute("error", "Unable to update cloth");
            request.setAttribute("cloth", cloth);
            request.getRequestDispatcher("/WEB-INF/pages/edit_cloth.jsp")
                    .forward(request, response);
        }
    }
}