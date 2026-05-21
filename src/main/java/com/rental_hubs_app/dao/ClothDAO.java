package com.rental_hubs_app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rental_hubs_app.model.ClothModel;
import com.rental_hubs_app.utils.DBconfig;

public class ClothDAO {

    public boolean addCloth(ClothModel cloth) {

        String sql = "INSERT INTO clothes(cloth_name, category, size, color, rental_price, description, image_name, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cloth.getClothName());
            ps.setString(2, cloth.getCategory());
            ps.setString(3, cloth.getSize());
            ps.setString(4, cloth.getColor());
            ps.setDouble(5, cloth.getRentalPrice());
            ps.setString(6, cloth.getDescription());
            ps.setString(7, cloth.getImageName());
            ps.setString(8, cloth.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<ClothModel> getAllClothes() {

        List<ClothModel> clothes = new ArrayList<>();
        String sql = "SELECT * FROM clothes ORDER BY cloth_id DESC";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ClothModel cloth = new ClothModel();

                cloth.setClothId(rs.getInt("cloth_id"));
                cloth.setClothName(rs.getString("cloth_name"));
                cloth.setCategory(rs.getString("category"));
                cloth.setSize(rs.getString("size"));
                cloth.setColor(rs.getString("color"));
                cloth.setRentalPrice(rs.getDouble("rental_price"));
                cloth.setDescription(rs.getString("description"));
                cloth.setImageName(rs.getString("image_name"));
                cloth.setStatus(rs.getString("status"));

                clothes.add(cloth);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clothes;
    }

    public ClothModel getClothById(int clothId) {

        String sql = "SELECT * FROM clothes WHERE cloth_id = ?";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, clothId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ClothModel cloth = new ClothModel();

                cloth.setClothId(rs.getInt("cloth_id"));
                cloth.setClothName(rs.getString("cloth_name"));
                cloth.setCategory(rs.getString("category"));
                cloth.setSize(rs.getString("size"));
                cloth.setColor(rs.getString("color"));
                cloth.setRentalPrice(rs.getDouble("rental_price"));
                cloth.setDescription(rs.getString("description"));
                cloth.setImageName(rs.getString("image_name"));
                cloth.setStatus(rs.getString("status"));

                return cloth;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateCloth(ClothModel cloth) {

        String sql = "UPDATE clothes SET cloth_name = ?, category = ?, size = ?, color = ?, "
                + "rental_price = ?, description = ?, image_name = ?, status = ? WHERE cloth_id = ?";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cloth.getClothName());
            ps.setString(2, cloth.getCategory());
            ps.setString(3, cloth.getSize());
            ps.setString(4, cloth.getColor());
            ps.setDouble(5, cloth.getRentalPrice());
            ps.setString(6, cloth.getDescription());
            ps.setString(7, cloth.getImageName());
            ps.setString(8, cloth.getStatus());
            ps.setInt(9, cloth.getClothId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteCloth(int clothId) {

        String sql = "DELETE FROM clothes WHERE cloth_id = ?";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, clothId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public int getTotalClothes() {

        String sql = "SELECT COUNT(*) FROM clothes";

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
    public List<ClothModel> searchClothes(String keyword) {

        List<ClothModel> clothes = new ArrayList<>();

        String sql = "SELECT * FROM clothes "
                + "WHERE cloth_name LIKE ? "
                + "OR category LIKE ? "
                + "OR size LIKE ? "
                + "OR color LIKE ? "
                + "OR status LIKE ? "
                + "ORDER BY cloth_id DESC";

        try (Connection conn = DBconfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String searchValue = "%" + keyword + "%";

            ps.setString(1, searchValue);
            ps.setString(2, searchValue);
            ps.setString(3, searchValue);
            ps.setString(4, searchValue);
            ps.setString(5, searchValue);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ClothModel cloth = new ClothModel();

                cloth.setClothId(rs.getInt("cloth_id"));
                cloth.setClothName(rs.getString("cloth_name"));
                cloth.setCategory(rs.getString("category"));
                cloth.setSize(rs.getString("size"));
                cloth.setColor(rs.getString("color"));
                cloth.setRentalPrice(rs.getDouble("rental_price"));
                cloth.setDescription(rs.getString("description"));
                cloth.setImageName(rs.getString("image_name"));
                cloth.setStatus(rs.getString("status"));

                clothes.add(cloth);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clothes;
    }
}