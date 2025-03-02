package main.java.repositories;
import main.java.utils.DataBaseConnection;

import main.java.models.Client;
import java.sql.*;

public class ClientRepository {
    private Connection connection;

    public ClientRepository() {
        this.connection = DataBaseConnection.getConnection();
    }

    public boolean addClient(Client client) {
        String insertUserQuery = "INSERT INTO User (firstName, lastName, email, password, address, role) VALUES (?, ?, ?, ?, ?, ?)";
        String insertClientQuery = "INSERT INTO Client (userID, phone) VALUES (?, ?)";

        try (PreparedStatement userStmt = connection.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS)) {
            // Insertion dans User
            userStmt.setString(1, client.getFirstName());
            userStmt.setString(2, client.getLastName());
            userStmt.setString(3, client.getEmail());
            userStmt.setString(4, client.getPassword());
            userStmt.setString(5, client.getAddress());
            userStmt.setString(6, client.getRole());

            int affectedRows = userStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("L'insertion de l'utilisateur a échoué.");
            }

            // Récupération du userID généré
            try (ResultSet generatedKeys = userStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int userID = generatedKeys.getInt(1);
                    // Insertion dans Client
                    try (PreparedStatement clientStmt = connection.prepareStatement(insertClientQuery)) {
                        clientStmt.setInt(1, userID);
                        clientStmt.setString(2, client.getPhone());
                        return clientStmt.executeUpdate() > 0;
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

    public Client getClientByUserID(int userID) {
        String query = "SELECT u.*, c.phone FROM User u INNER JOIN Client c ON u.userID = c.userID WHERE u.userID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Client(
                    userID,
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("address"),
                    rs.getString("role"),
                    rs.getString("phone")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
