package main.java.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import main.java.models.User;
import main.java.utils.DataBaseConnection;

public class UserRepository {
    public boolean addUser(User user) {
        try (Connection conn = DataBaseConnection.getConnection()) {
            String query = "INSERT INTO User (firstName, lastName, email, password, address) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword()); // ⚠️ À hacher avant stockage
            stmt.setString(5, user.getAddress());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Retourne true si l'utilisateur a été ajouté avec succès
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public User getUserByEmail(String email) {
        try (Connection conn = DataBaseConnection.getConnection()) {
            String query = "SELECT * FROM User WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt("userID"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"),rs.getString("address"),rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
