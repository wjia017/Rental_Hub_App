package com.rental_hubs_app.service;

import java.util.List;

import com.rental_hubs_app.dao.ClothDAO;
import com.rental_hubs_app.model.ClothModel;

public class ClothService {

    private ClothDAO clothDAO = new ClothDAO();

    public String addCloth(ClothModel cloth) {

        if (cloth.getClothName() == null || cloth.getClothName().trim().isEmpty()) {
            return "Cloth name is required";
        }

        if (cloth.getCategory() == null || cloth.getCategory().trim().isEmpty()) {
            return "Category is required";
        }

        if (cloth.getSize() == null || cloth.getSize().trim().isEmpty()) {
            return "Size is required";
        }

        if (cloth.getRentalPrice() <= 0) {
            return "Rental price must be greater than zero";
        }

        boolean result = clothDAO.addCloth(cloth);

        if (result) {
            return "success";
        }

        return "Unable to add cloth";
    }

    public List<ClothModel> getAllClothes() {
        return clothDAO.getAllClothes();
    }

    public ClothModel getClothById(int clothId) {
        return clothDAO.getClothById(clothId);
    }

    public boolean updateCloth(ClothModel cloth) {
        return clothDAO.updateCloth(cloth);
    }

    public boolean deleteCloth(int clothId) {
        return clothDAO.deleteCloth(clothId);
    }
}