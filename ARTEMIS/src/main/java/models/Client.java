package main.java.models;
public class Client extends User {
    private String phone;

    public Client(int userID, String firstName, String lastName, String email, String password, String address,String role, String phone) {
        super(userID, firstName, lastName, email, password, address,role);
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client{" + super.toString() + ", phone='" + phone + "'}";
    }
}

