package com.artemis.demo.service;

import com.artemis.demo.model.Product;
import com.artemis.demo.model.ShoppingCart;
import com.artemis.demo.model.User;
import com.artemis.demo.repository.ProductRepository;
import com.artemis.demo.repository.ShoppingCartRepository;
import com.artemis.demo.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
@Transactional
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    // Get the shopping cart for a user
    public ShoppingCart getCartByUserId(Long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserUserID(userId);

        if (cart == null) {
            cart = new ShoppingCart();
            cart.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"))); // Ensure the user is set
            cart.setItems(new ArrayList<>()); // Initialize the items list
            cart.setTotalPrice(BigDecimal.ZERO); // Initialize the total price
            shoppingCartRepository.save(cart);
        }

        return cart;
    }

    // Add a product to the shopping cart
    public void addProductToCart(Long userId, Long productId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        ShoppingCart cart = shoppingCartRepository.findByUserUserID(user.getUserID());

        if (cart == null) {
            cart = new ShoppingCart();
            cart.setUser(user); // Ensure the user is set
            cart.setItems(new ArrayList<>()); // Initialize the items list
            cart.setTotalPrice(BigDecimal.ZERO); // Initialize the total price
        }

        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if the product is already in the cart
        for (Product existingProduct : cart.getItems()) {
            if (existingProduct.getProductID().equals(productId)) {
                throw new RuntimeException("Product is already in the cart");
            }
        }

        cart.getItems().add(product);
        cart.recalculateTotalPrice(); // Recalculate the total price after adding the product
        shoppingCartRepository.save(cart);
    }


    // Remove a product from the shopping cart
    public void removeProductFromCart(Long userId, Long productId) {
        ShoppingCart cart = shoppingCartRepository.findByUserUserID(userId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cart.getItems().remove(product);
        cart.recalculateTotalPrice();
        shoppingCartRepository.save(cart);
    }

    // Clear the shopping cart
    public void clearCart(Long userId) {
        ShoppingCart cart = getCartByUserId(userId);
        cart.getItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        shoppingCartRepository.save(cart);
    }
}
