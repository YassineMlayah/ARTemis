package main.java.models;
public class Artist extends User {
    private String speciality;

    public Artist(int userID, String firstName, String lastName, String email, String password, String address,String role, String speciality) {
        super(userID, firstName, lastName, email, password, address,role);
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Artist{" + super.toString() + ", speciality='" + speciality + "'}";
    }
}

