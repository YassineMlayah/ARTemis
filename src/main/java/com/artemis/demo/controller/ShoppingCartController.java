package com.artemis.demo.controller;

import com.artemis.demo.model.ShoppingCart;
import com.artemis.demo.model.User;
import com.artemis.demo.service.ShoppingCartService;

import jakarta.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {

    final private ShoppingCartService shoppingCartService;
    final private HttpSession session;


    public ShoppingCartController(ShoppingCartService shoppingCartService, HttpSession session) {
        this.shoppingCartService = shoppingCartService;
        this.session = session;
    }

    // Get the user's shopping cart
    @GetMapping("/user/{userId}")
    public ResponseEntity<ShoppingCart> getCart(@PathVariable Long userId) {
        return ResponseEntity.ok(shoppingCartService.getCartByUserId(userId));
    }

    @GetMapping("/user/current")
    public ResponseEntity<ShoppingCart> getCurrentCart() {
        User user = (User) session.getAttribute("user");
        return ResponseEntity.ok(shoppingCartService.getCartByUserId(user.getUserID()));
    }

    // Add a product to the shopping cart
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Long userId, @RequestParam Long productId) {
        shoppingCartService.addProductToCart(userId, productId);
        return ResponseEntity.ok("Product added to cart successfully");
    }

    // Add a product to the current user's shopping cart
    @PostMapping("/add/current/{productId}")
    public ResponseEntity<String> addToCurrentCart(@PathVariable Long productId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        shoppingCartService.addProductToCart(user.getUserID(), productId);
        return ResponseEntity.ok("Product added to cart successfully");
    }

    // Remove a product from the shopping cart
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestParam(required = false) Long userId,
                                                 @RequestParam Long productId,
                                                 HttpSession session) {
        if (userId == null) {
            User user = (User) session.getAttribute("user");
            userId = user.getUserID();
        }
        shoppingCartService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok("Product removed from cart successfully");
    }

    // Clear the shopping cart
    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
        if (userId == null) {
            User user = (User) session.getAttribute("user");
            userId = user.getUserID();
        }
        shoppingCartService.clearCart(userId);
        return ResponseEntity.ok("Cart cleared successfully");
    }
}
