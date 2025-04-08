package com.artemis.demo.controller;

import com.artemis.demo.model.Product;
import com.artemis.demo.model.User;
import com.artemis.demo.service.ProductService;
import com.artemis.demo.model.enums.Category; // Import the Category enum
import com.artemis.demo.model.enums.Role;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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
    @PostMapping("/create")
    public Product createProduct(
        @RequestParam("name") String name,
        @RequestParam("price") BigDecimal price,
        @RequestParam("category") Category category,
        @RequestParam("description") String description,
        @RequestParam("availability") boolean availability,
        @RequestParam("photo") MultipartFile photo,
        HttpSession session) throws IOException {

        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != Role.ARTIST) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only artists can create products!");
        }

        String imagePath = null;

        if (!photo.isEmpty()) {
            // Path to static/images (during development)
            String staticImagesPath = new File("src/main/resources/static/images/").getAbsolutePath();
            File directory = new File(staticImagesPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Unique filename to avoid collisions
            String originalFilename = photo.getOriginalFilename();
            String extension = originalFilename != null && originalFilename.contains(".")
                    ? originalFilename.substring(originalFilename.lastIndexOf("."))
                    : "";
            String uniqueFilename = java.util.UUID.randomUUID() + extension;

            // Full path where the image will be saved
            File destination = new File(directory, uniqueFilename);
            photo.transferTo(destination);

            // Relative path for frontend access (e.g., /images/abc123.jpg)
            imagePath = "/images/" + uniqueFilename;
        }

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setCategory(category);
        product.setDescription(description);
        product.setAvailable(availability);
        product.setImagePath(imagePath);
        product.setArtist(user);

        return productService.createProduct(product, user.getUserID());
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
        Product existingProduct = productService.getProductById(productId);

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setAvailable(product.isAvailable());

        return productService.updateProduct(productId, existingProduct);
    }

    // Delete Product
    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }

    // Filter Products
    @GetMapping("/filter")
    public List<Product> filterProducts(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sortOption,
            @RequestParam(required = false) String artistName) {

        Category categoryEnum = null;
        if (category != null && !"all".equalsIgnoreCase(category)) {
            try {
                categoryEnum = Category.valueOf(category.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category value: " + category);
            }
        }

        return productService.filterProducts(minPrice, maxPrice, categoryEnum, sortOption, artistName);
    }

    // Get Top 6 Products
    @GetMapping("/top6")
    public List<Product> getTop6Products() {
        return productService.getTop6Products();
    }
}
