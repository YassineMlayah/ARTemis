package com.artemis.demo.model;

import java.math.BigDecimal;

import com.artemis.demo.model.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;

    private String name;
    private BigDecimal price;

    @Column(nullable = true)
    private String imagePath;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(columnDefinition = "TEXT")
    private String description;

    private boolean available = true;

    // Many-to-One relationship: A product belongs to one artist
    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)  // FK reference to User entity
    private User artist;  // The artist who created the product

    // Getters and Setters (generated by Lombok)
}
