package com.artemis.demo.model;

import com.artemis.demo.model.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Creates a separate table for each subclass
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Invalid email format")  // Ensures valid email format
    @NotBlank(message = "Email is required")  // Ensures email is not blank
    @Column(unique = true)  // Ensures email uniqueness in the database
    private String email;

    @NotBlank(message = "Password is required")  // Ensures password is not blank
    @Size(min = 6, message = "Password must be at least 6 characters long")  // Password length validation
    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.CLIENT;  // Stores user role as "CLIENT", "ARTIST", or "ADMIN"

    // Getters and Setters (generated by Lombok)

}
