package com.artemis.demo.controller;

import com.artemis.demo.model.Product;
import com.artemis.demo.model.User;
import com.artemis.demo.service.ProductService;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final HttpSession session;

    public ProductController(ProductService productService, HttpSession session) {
        this.productService = productService;
        this.session = session;
    }

    // Create Product (Only for Artists)
    @PostMapping("/create/{userId}")
    public Product createProduct(@RequestBody Product product, @PathVariable Long userId) {
        if (userId == null) {
            User user = (User) session.getAttribute("user");
            userId = user.getUserID();
        }
        return productService.createProduct(product, userId);
    }

    // Get All Products
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Get Products by Artist
    @GetMapping("/artist/{artistId}")
    public List<Product> getProductsByArtist(@PathVariable Long artistId) {
        return productService.getProductsByArtist(artistId);
    }

    // Get Products of current user
    @GetMapping("/artist/current")
    public List<Product> getCurrentUserProducts() {
        User user = (User) session.getAttribute("user");
                return productService.getProductsByArtist(user.getUserID());
    }

    // Get Product by ID
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    // Update Product
    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    // Delete Product
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
}
