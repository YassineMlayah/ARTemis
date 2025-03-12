package main.java.models;

import java.util.ArrayList;
import java.util.List;

public class Artist extends User {
    private String speciality;
    private List<Product> products;

    public Artist(int userID, String firstName, String lastName, String email, String password, String address,String role, String speciality) {
        super(userID, firstName, lastName, email, password, address,role);
        this.speciality = speciality;
        this.products = new ArrayList<>();
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public String toString() {
        return "Artist{" + super.toString() + ", speciality='" + speciality + '\''+
                ", products=" + products +
                '}';
    }
}

