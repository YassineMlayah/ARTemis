package main.java.repositories;

import main.java.models.Product;
import main.java.utils.DataBaseConnection;
import java.sql.*;

public class ProductRepository {
    private Connection connection;

    public ProductRepository() {
        this.connection = DataBaseConnection.getConnection();
    }

    public boolean addProduct(Product product, int artistID) {
        String query = "INSERT INTO Product (name, price, type, description, disponibility, photo, artistID) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setString(3, product.getType());
            stmt.setString(4, product.getDescription());
            stmt.setBoolean(5, product.isDisponibility());
            stmt.setString(6, product.getPhoto());
            stmt.setInt(7, artistID);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("L'ajout du produit a échoué.");
            }

            // Récupération de l'ID généré
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setProductID(generatedKeys.getInt(1));
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
