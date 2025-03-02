package main.java.models;


public class UserProduct {
    // Attributs
    private int userID;
    private int productID;
    private int quantity;


    // Constructeurs
    public UserProduct() {
        // Constructeur par défaut
    }

    public UserProduct(int userID, int productID, int quantity) {
        this.userID = userID;
        this.productID = productID;
        this.quantity = quantity;
    }

    // Getters et Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "UserProduct{" +
                "userID=" + userID +
                ", productID=" + productID +
                ", quantity=" + quantity +
                '}';
    }
}
