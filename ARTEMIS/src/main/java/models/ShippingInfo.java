package main.java.models;

public class ShippingInfo {
    // Attributs
    private int shippingID;
    private int userID;
    private String shippingStatus;
    private String shippingMethod;
    private String carrier;
    private Date deliveryDate;
    private String shippingAddress;
    private double shippingCost;
    private String notes;

    // Constructeurs
    public ShippingInfo() {
        // Constructeur par défaut
    }

    public ShippingInfo(int shippingID, int userID, String shippingStatus, String shippingMethod, String carrier, Date deliveryDate, String shippingAddress, double shippingCost, String notes) {
        this.shippingID = shippingID;
        this.userID = userID;
        this.shippingStatus = shippingStatus;
        this.shippingMethod = shippingMethod;
        this.carrier = carrier;
        this.deliveryDate = deliveryDate;
        this.shippingAddress = shippingAddress;
        this.shippingCost = shippingCost;
        this.notes = notes;
    }

    public ShippingInfo(int userID, String shippingStatus, String shippingMethod, String carrier, Date deliveryDate, String shippingAddress, double shippingCost, String notes) {
        this.userID = userID;
        this.shippingStatus = shippingStatus;
        this.shippingMethod = shippingMethod;
        this.carrier = carrier;
        this.deliveryDate = deliveryDate;
        this.shippingAddress = shippingAddress;
        this.shippingCost = shippingCost;
        this.notes = notes;
    }

    // Getters et Setters
    public int getShippingID() {
        return shippingID;
    }

    public void setShippingID(int shippingID) {
        this.shippingID = shippingID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "ShippingInfo{" +
                "shippingID=" + shippingID +
                ", userID=" + userID +
                ", shippingStatus='" + shippingStatus + '\'' +
                ", shippingMethod='" + shippingMethod + '\'' +
                ", carrier='" + carrier + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", shippingCost=" + shippingCost +
                ", notes='" + notes + '\'' +
                '}';
    }
}
