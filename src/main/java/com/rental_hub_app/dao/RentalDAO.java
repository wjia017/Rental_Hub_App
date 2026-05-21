package com.rental_hub_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rental_hub_app.model.RentalModel;
import com.rental_hub_app.utils.DBconfig;

public class RentalDAO {

    public boolean requestRental(RentalModel rental) {

        String sql = "INSERT INTO rentals(user_id, cloth_id, rental_date, return_date, total_price, status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, rental.getUserId());
            ps.setInt(2, rental.getClothId());
            ps.setDate(3, rental.getRentalDate());
            ps.setDate(4, rental.getReturnDate());
            ps.setDouble(5, rental.getTotalPrice());
            ps.setString(6, "pending");

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<RentalModel> getRentalsByUser(int userId) {

        List<RentalModel> rentals = new ArrayList<>();

        String sql = "SELECT r.*, c.cloth_name FROM rentals r "
                + "JOIN clothes c ON r.cloth_id = c.cloth_id "
                + "WHERE r.user_id = ? ORDER BY r.rental_id DESC";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RentalModel rental = new RentalModel();

                rental.setRentalId(rs.getInt("rental_id"));
                rental.setUserId(rs.getInt("user_id"));
                rental.setClothId(rs.getInt("cloth_id"));
                rental.setClothName(rs.getString("cloth_name"));
                rental.setRentalDate(rs.getDate("rental_date"));
                rental.setReturnDate(rs.getDate("return_date"));
                rental.setTotalPrice(rs.getDouble("total_price"));
                rental.setStatus(rs.getString("status"));

                rentals.add(rental);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rentals;
    }

    public List<RentalModel> getAllRentals() {

        List<RentalModel> rentals = new ArrayList<>();

        String sql = "SELECT r.*, c.cloth_name, u.username FROM rentals r "
                + "JOIN clothes c ON r.cloth_id = c.cloth_id "
                + "JOIN users u ON r.user_id = u.user_id "
                + "ORDER BY r.rental_id DESC";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RentalModel rental = new RentalModel();

                rental.setRentalId(rs.getInt("rental_id"));
                rental.setUserId(rs.getInt("user_id"));
                rental.setClothId(rs.getInt("cloth_id"));
                rental.setClothName(rs.getString("cloth_name"));
                rental.setUsername(rs.getString("username"));
                rental.setRentalDate(rs.getDate("rental_date"));
                rental.setReturnDate(rs.getDate("return_date"));
                rental.setTotalPrice(rs.getDouble("total_price"));
                rental.setStatus(rs.getString("status"));

                rentals.add(rental);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rentals;
    }

    public boolean updateRentalStatus(int rentalId, String status) {

        String sql = "UPDATE rentals SET status = ? WHERE rental_id = ?";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, rentalId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public int getActiveRentalCountByUser(int userId) {

        String sql = "SELECT COUNT(*) FROM rentals WHERE user_id = ? AND status = 'approved'";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public double getTotalSpentByUser(int userId) {

        String sql = "SELECT COALESCE(SUM(total_price), 0) FROM rentals WHERE user_id = ? AND status IN ('approved', 'returned')";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getPendingRentalCountByUser(int userId) {

        String sql = "SELECT COUNT(*) FROM rentals WHERE user_id = ? AND status = 'pending'";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
    public int getActiveRentalCount() {

        String sql = "SELECT COUNT(*) FROM rentals WHERE status = 'approved'";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public double getTotalRevenue() {

        String sql = "SELECT COALESCE(SUM(total_price), 0) FROM rentals WHERE status IN ('approved', 'returned')";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}