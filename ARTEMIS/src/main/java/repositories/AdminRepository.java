package main.java.repositories;

import main.java.models.Admin;
import main.java.utils.DataBaseConnection;
import java.sql.*;

public class AdminRepository {
    private Connection connection;

    public AdminRepository() {
    this.connection = DataBaseConnection.getConnection();
    }

    public boolean addAdmin(Admin admin) {
    String insertUserQuery = "INSERT INTO User (firstName, lastName, email, password, address, role) VALUES (?, ?, ?, ?, ?, ?)";
    String insertAdminQuery = "INSERT INTO Admin (userID) VALUES (?)";

    try (PreparedStatement userStmt = connection.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS)) {
        // Insertion dans User
        userStmt.setString(1, admin.getFirstName());
        userStmt.setString(2, admin.getLastName());
        userStmt.setString(3, admin.getEmail());
        userStmt.setString(4, admin.getPassword());
        userStmt.setString(5, admin.getAddress());
        userStmt.setString(6, admin.getRole());

        int affectedRows = userStmt.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("L'insertion de l'utilisateur a échoué.");
        }

        // Récupération du userID généré
        try (ResultSet generatedKeys = userStmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int userID = generatedKeys.getInt(1);
                // Insertion dans Admin
                try (PreparedStatement adminStmt = connection.prepareStatement(insertAdminQuery)) {
                    adminStmt.setInt(1, userID);
                    return adminStmt.executeUpdate() > 0;
                }
            } else {
                throw new SQLException("L'insertion de l'utilisateur a échoué, aucun ID généré.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

public Admin getAdminByEmail(String email) {
    String query = "SELECT * FROM User u INNER JOIN Admin a ON u.userID = a.userID WHERE u.email = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Admin(
                rs.getInt("userID"),
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getString("address"),
                rs.getString("role")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
}
