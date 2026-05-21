package com.rental_hubs_app.controller;

import java.io.File;
import java.io.IOException;

import com.rental_hub_app.dao.UserDAO;
import com.rental_hub_app.model.UserModel;
import com.rental_hub_app.utils.FileUploadUtil;
import com.rental_hub_app.utils.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/profile")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class ProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR =
            System.getProperty("user.home") + File.separator + "rental_hub_uploads";

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserModel loggedUser = (UserModel) SessionUtil.getAttribute(request, "loggedUser");

        if (loggedUser == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        loggedUser.setFirstName(firstName);
        loggedUser.setLastName(lastName);
        loggedUser.setEmail(email);
        loggedUser.setPhone(phone);
        loggedUser.setAddress(address);

        boolean updated = userDAO.updateProfile(loggedUser);

        Part filePart = request.getPart("profile_image");

        if (filePart != null && filePart.getSize() > 0) {

            if (FileUploadUtil.isImage(filePart)) {

                String extension = FileUploadUtil.getFileExtension(filePart.getSubmittedFileName());
                String fileName = "user_" + loggedUser.getUserId() + extension;

                FileUploadUtil.saveFile(filePart, UPLOAD_DIR, fileName);

                userDAO.updateProfileImage(loggedUser.getUserId(), fileName);
                loggedUser.setProfileImage(fileName);

            } else {
                request.setAttribute("error", "Only image files are allowed");
                request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
                return;
            }
        }

        if (updated) {
            SessionUtil.setAttribute(request, "loggedUser", loggedUser);
            request.setAttribute("success", "Profile updated successfully");
        } else {
            request.setAttribute("error", "Unable to update profile");
        }

        request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
    }
}