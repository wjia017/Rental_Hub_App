package com.rental_hub_app.service;

import com.rental_hub_app.dao.UserDAO;
import com.rental_hub_app.model.UserModel;
import com.rental_hub_app.utils.PasswordUtil;

public class RegisterService {

    private UserDAO userDAO = new UserDAO();

    public String registerUser(UserModel user) {

        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            return "First name is required";
        }

        if (!user.getFirstName().matches("^[A-Za-z ]+$")) {
            return "First name must contain only letters";
        }

        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            return "Last name is required";
        }

        if (!user.getLastName().matches("^[A-Za-z ]+$")) {
            return "Last name must contain only letters";
        }

        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return "Username is required";
        }

        if (userDAO.isUsernameExists(user.getUsername())) {
            return "Username already exists";
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            return "Email is required";
        }

        if (!user.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            return "Enter a valid email address";
        }

        if (userDAO.isEmailExists(user.getEmail())) {
            return "Email already exists";
        }

        if (user.getPhone() == null || user.getPhone().trim().isEmpty()) {
            return "Phone number is required";
        }

        if (!user.getPhone().matches("^[0-9]{10}$")) {
            return "Phone number must be 10 digits";
        }

        if (userDAO.isPhoneExists(user.getPhone())) {
            return "Phone number already exists";
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            return "Password must be at least 6 characters";
        }

        String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);

        boolean isRegistered = userDAO.registerUser(user);

        if (isRegistered) {
            return "success";
        }

        return "Something went wrong. Please try again";
    }
}