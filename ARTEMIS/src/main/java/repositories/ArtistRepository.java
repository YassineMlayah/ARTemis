package main.java.repositories;

import main.java.models.Artist;
import java.sql.*;
import main.java.utils.DataBaseConnection;
public class ArtistRepository {
    private Connection connection;

    public ArtistRepository() {
        this.connection = DataBaseConnection.getConnection();
    }

    public boolean addArtist(Artist artist) {
        String insertUserQuery = "INSERT INTO User (firstName, lastName, email, password, address, role) VALUES (?, ?, ?, ?, ?, ?)";
        String insertArtistQuery = "INSERT INTO Artist (userID, speciality) VALUES (?, ?)";

        try (PreparedStatement userStmt = connection.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS)) {
            // Insertion dans User
            userStmt.setString(1, artist.getFirstName());
            userStmt.setString(2, artist.getLastName());
            userStmt.setString(3, artist.getEmail());
            userStmt.setString(4, artist.getPassword());
            userStmt.setString(5, artist.getAddress());
            userStmt.setString(6, artist.getRole());

            int affectedRows = userStmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("L'insertion de l'utilisateur a échoué.");
            }

            // Récupération du userID généré
            try (ResultSet generatedKeys = userStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int userID = generatedKeys.getInt(1);
                    // Insertion dans Artist
                    try (PreparedStatement artistStmt = connection.prepareStatement(insertArtistQuery)) {
                        artistStmt.setInt(1, userID);
                        artistStmt.setString(2, artist.getSpeciality());
                        return artistStmt.executeUpdate() > 0;
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

    public Artist getArtistByUserID(int userID) {
        String query = "SELECT u.*, a.speciality FROM User u INNER JOIN Artist a ON u.userID = a.userID WHERE u.userID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Artist(
                    userID,
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("address"),
                    rs.getString("role"),
                    rs.getString("speciality")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
