package main.java.models;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private int cartID;
    private int userID;
    private List<Product> items;
    private double totalPrice;
    private String status;
    // Constructeurs
    public ShoppingCart() {
        // Constructeur par défaut
    }
    
    public ShoppingCart(int cartID, int userID, String status) {
        this.cartID = cartID;
        this.userID = userID;
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
        this.status = status;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public List<Product> getItems() {
        return items;
    }

    public void addItem(Product product) {
        items.add(product);
        totalPrice += product.getPrice();
    }

    public void removeItem(Product product) {
        if (items.remove(product)) {
            totalPrice -= product.getPrice();
        }
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartID=" + cartID +
                ", userID=" + userID +
                ", items=" + items +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                '}';
    }
}

