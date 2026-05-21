package com.rental_hub_app.service;

import com.rental_hub_app.dao.UserDAO;
import com.rental_hub_app.model.UserModel;
import com.rental_hub_app.utils.PasswordUtil;

public class LoginService {

    private UserDAO userDAO = new UserDAO();

    public UserModel loginUser(String username, String password) {

        if (username == null || username.trim().isEmpty()) {
            return null;
        }

        if (password == null || password.trim().isEmpty()) {
            return null;
        }

        UserModel user = userDAO.getUserByUsername(username);

        if (user == null) {
            return null;
        }

        if (!"active".equalsIgnoreCase(user.getStatus())) {
            return null;
        }

        boolean isPasswordCorrect = PasswordUtil.checkPassword(password, user.getPassword());

        if (isPasswordCorrect) {
            return user;
        }

        return null;
    }
}