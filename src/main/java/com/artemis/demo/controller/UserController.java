package com.artemis.demo.controller;

import com.artemis.demo.model.User;
import com.artemis.demo.service.UserService;
import com.artemis.demo.util.ApiResponse;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    final private UserService userService;
    final private HttpSession session;


    public UserController(UserService userService, HttpSession session) {
        this.userService = userService;
        this.session = session;
    }

    // Register a new user (Signup)
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getFieldErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .reduce((msg1, msg2) -> msg1 + ", " + msg2)
                    .orElse("Invalid data");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(errorMessage, false));
        }

        Optional<User> registeredUser = userService.registerUser(user);

        if (registeredUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Email already in use", false));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("User registered successfully", true));
    }

/*
    // Login user
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody User loginUser) {
        boolean isValid = userService.validateUserCredentials(loginUser.getEmail(), loginUser.getPassword());

        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse("Invalid credentials", false));
        }

        return ResponseEntity.ok(new ApiResponse("Login successful!", true));
    }
*/

    // Get all users
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get current user
    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser() {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.ok(user);
    }

    // Get user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {

        Optional<User> updated = userService.updateUser(id, updatedUser);

        if (updated.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("User not found", false));
        }
        return ResponseEntity.ok(new ApiResponse("User updated successfully", true));
    }

    // Update current user
    @PutMapping("/current")
    public ResponseEntity<ApiResponse> updateCurrentUser(@Valid @RequestBody User updatedUser) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse("No user logged in", false));
        }

        Optional<User> updated = userService.updateUser(user.getUserID(), updatedUser);

        if (updated.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("User not found", false));
        }
        return ResponseEntity.ok(new ApiResponse("User updated successfully", true));
    }

    // Delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        if (userService.getUserById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("User not found", false));
        }

        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("User deleted successfully", true));
    }

    // Delete current user
    @DeleteMapping("/current")
    public ResponseEntity<ApiResponse> deleteCurrentUser() {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse("No user logged in", false));
        }

        userService.deleteUser(user.getUserID());
        session.invalidate(); // Invalidate the session after deletion
        return ResponseEntity.ok(new ApiResponse("User deleted successfully", true));
    }
}
