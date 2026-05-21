package com.rental_hubs_app.model;

public class ClothModel {

    private int clothId;
    private String clothName;
    private String category;
    private String size;
    private String color;
    private double rentalPrice;
    private String description;
    private String imageName;
    private String status;

    public ClothModel() {
    }

    public ClothModel(String clothName, String category, String size, String color,
            double rentalPrice, String description, String imageName, String status) {

        this.clothName = clothName;
        this.category = category;
        this.size = size;
        this.color = color;
        this.rentalPrice = rentalPrice;
        this.description = description;
        this.imageName = imageName;
        this.status = status;
    }

    public ClothModel(String clothName, String category, String size, String color,
            double rentalPrice, String description, String status) {

        this.clothName = clothName;
        this.category = category;
        this.size = size;
        this.color = color;
        this.rentalPrice = rentalPrice;
        this.description = description;
        this.status = status;
    }

    public int getClothId() {
        return clothId;
    }

    public void setClothId(int clothId) {
        this.clothId = clothId;
    }

    public String getClothName() {
        return clothName;
    }

    public void setClothName(String clothName) {
        this.clothName = clothName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}