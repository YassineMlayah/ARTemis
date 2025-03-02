package main.java.models;
public class Admin extends User {
    

    public Admin(int userID, String firstName, String lastName, String email, String password, String address, String role) {
        super(userID, firstName, lastName, email, password, address, role);
        
    }



    @Override
    public String toString() {
        return "Admin{" + super.toString() +  "'}";
    }
}
