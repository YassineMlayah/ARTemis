package main.java.models;
public class Category {
    // Attributs
    private int categoryID;
    private String categoryName;
    private boolean customisation;

    // Constructeurs
    public Category() {
        // Constructeur par défaut
    }

    public Category(int categoryID, String categoryName, boolean customisation) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.customisation = customisation;
    }

    public Category(String categoryName, boolean customisation) {
        this.categoryName = categoryName;
        this.customisation = customisation;
    }

    // Getters et Setters
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isCustomisation() {
        return customisation;
    }

    public void setCustomisation(boolean customisation) {
        this.customisation = customisation;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Category{" +
                "categoryID=" + categoryID +
                ", categoryName='" + categoryName + '\'' +
                ", customisation=" + customisation +
                '}';
    }
}
