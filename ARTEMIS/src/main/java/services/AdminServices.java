package main.java.services;

import main.java.models.Admin;
import main.java.repositories.AdminRepository;

public class AdminServices {
    private final AdminRepository adminRepository = new AdminRepository();

    public boolean signUp(Admin admin) {
        if (adminRepository.getAdminByEmail(admin.getEmail()) == null) {
            return adminRepository.addAdmin(admin);
        }
        return false; // L'admin existe déjà
    }

    public Admin login(String email, String password) {
        Admin admin = adminRepository.getAdminByEmail(email);
        if (admin != null && admin.getPassword().equals(password)) {
            return admin;
        }
        return null; // Échec de l'authentification
    }
}
