package com.rental_hubs_app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/get-cloth-image")
public class GetClothImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR =
            System.getProperty("user.home") + File.separator + "rental_hub_clothes";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String fileName = request.getParameter("file");

        if (fileName == null || fileName.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        File imageFile = new File(UPLOAD_DIR, fileName);

        if (!imageFile.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String contentType = getServletContext().getMimeType(imageFile.getName());

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        response.setContentType(contentType);
        Files.copy(imageFile.toPath(), response.getOutputStream());
    }
}