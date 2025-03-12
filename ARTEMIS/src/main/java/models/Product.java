package main.java.models;
public class Product {
    private int productID;
    private String name;
    private double price;
    private String type;
    private String description;
    private boolean disponibility;
    private String photo;
    // Constructor
    public Product() {
        // Default constructor
    }

    public Product(int productID, String name, double price, String type, String description, boolean disponibility,String photo) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.type = type;
        this.description = description;
        this.disponibility = disponibility;
        this.photo = photo;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDisponibility() {
        return disponibility;
    }

    public void setDisponibility(boolean disponibility) {
        this.disponibility = disponibility;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", disponibility=" + disponibility +'\''+
                ", photo=" + photo +'\''+
                '}';
    }
}

