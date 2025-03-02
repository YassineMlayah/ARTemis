package main.java.models;
import java.sql.Timestamp;

public class Payment {
    // Attributs
    private int paymentID;
    private int userID;
    private String paymentStatus;
    private double amount;
    private String paymentMethod;
    private String cardNumber;
    private Timestamp paymentDate;

    // Constructeurs
    public Payment() {
        // Constructeur par défaut
    }

    public Payment(int paymentID, int userID, String paymentStatus, double amount, String paymentMethod, String cardNumber, Timestamp paymentDate) {
        this.paymentID = paymentID;
        this.userID = userID;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.paymentDate = paymentDate;
    }

    public Payment(int userID, String paymentStatus, double amount, String paymentMethod, String cardNumber) {
        this.userID = userID;
        this.paymentStatus = paymentStatus;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
    }

    // Getters et Setters
    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    // Méthode toString()
    @Override
    public String toString() {
        return "Payment{" +
                "paymentID=" + paymentID +
                ", userID=" + userID +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
