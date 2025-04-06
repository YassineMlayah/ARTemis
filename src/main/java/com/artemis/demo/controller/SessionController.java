package com.artemis.demo.controller;

import com.artemis.demo.model.User;
import com.artemis.demo.repository.UserRepository;
import com.artemis.demo.util.ApiResponse;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class SessionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                session.setAttribute("user", user);
                session.setMaxInactiveInterval(1800); // 30 minutes
                return ResponseEntity.status(HttpStatus.FOUND)
                        .header("Location", "/") // Redirect to the home page
                        .build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse("Invalid credentials", false));
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Session invalidated.";
    }

    @GetMapping("/session")
    public String checkSession(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "No user logged in.";
        }
        return "User is logged in as: " + user.getEmail();
    }

    @GetMapping("/user")
    public ResponseEntity<User> getSessionUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(user);
    }
}
