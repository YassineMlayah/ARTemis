package main.java.services;

import main.java.repositories.UserRepository;
import main.java.models.User;
//import org.mindrot.jbcrypt.BCrypt;

public class UserServices {
    private UserRepository userRepository = new UserRepository();

    public boolean signup(User user) {
        if (userRepository.getUserByEmail(user.getEmail()) != null) {
            System.out.println("Email déjà utilisé !");
            return false;
        }
    
        // Hachage du mot de passe
       // String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
       // user.setPassword(hashedPassword);
        
        return userRepository.addUser(user);
    }

    public User login(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        //mot de pass a hacher !!!!!!!!!!!!!!!!!
        //if (user != null && BCrypt.checkpw(password, user.getPassword())) {
       //     return user;
       // }
        return null;
    }
}
