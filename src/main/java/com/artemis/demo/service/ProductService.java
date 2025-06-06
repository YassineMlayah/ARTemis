package com.artemis.demo.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;

import com.artemis.demo.model.*;
import com.artemis.demo.model.enums.Category;
import com.artemis.demo.model.enums.Role;
import com.artemis.demo.repository.*;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService{

    final private ProductRepository productRepository;
    final private UserRepository userRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    // Create Product (only artists can create products)
    public Product createProduct(Product product, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getRole() != Role.ARTIST) {
            throw new IllegalArgumentException("Only artists can create products!");
        }

        product.setArtist(user);
        return productRepository.save(product);
    }

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get Products by Artist
    public List<Product> getProductsByArtist(Long artistId) {
        return productRepository.findByArtistUserID(artistId);
    }

    // Update Product
    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setAvailable(updatedProduct.isAvailable());

        return productRepository.save(existingProduct);
    }

    // Delete Product
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    // Get Product by ID
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    // Filter Products
    public List<Product> filterProducts(Double minPrice, Double maxPrice, Category category, String sortOption, String artistName) {
        List<Product> filtered = productRepository.filterProducts(minPrice, maxPrice, category, artistName);
        if (sortOption != null) {
            switch (sortOption) {
                case "lowToHigh":
                    filtered.sort(Comparator.comparing(Product::getPrice));
                    break;
                case "highToLow":
                    filtered.sort(Comparator.comparing(Product::getPrice).reversed());
                    break;
                case "alphabetical":
                    filtered.sort(Comparator.comparing(Product::getName));
                    break;
            }
        }
        return filtered;
    }

    // Get Top 6 Products
    public List<Product> getTop6Products() {
        return productRepository.findTop6Products(PageRequest.of(0, 6));
    }

}
