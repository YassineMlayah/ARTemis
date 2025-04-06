package com.artemis.demo.service;

import com.artemis.demo.model.Orders;
import com.artemis.demo.model.Product;
import com.artemis.demo.model.ShoppingCart;
import com.artemis.demo.repository.OrderRepository;
import com.artemis.demo.repository.ProductRepository;
import com.artemis.demo.repository.ShoppingCartRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
    }

    public Orders placeOrder(Long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserUserID(userId);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Shopping cart is empty");
        }

        // Ensure all products are available before placing the order
        for (Product product : cart.getItems()) {
            if (!product.isAvailable()) {
                throw new RuntimeException("One or more products in your cart are unavailable.");
            }
        }

        // Create and save the order only if all products are available
        Orders order = new Orders();
        order.setUser(cart.getUser());
        order.setItems(cart.getItems());
        order.setTotalPrice(cart.getTotalPrice());

        Orders savedOrder = orderRepository.save(order); // Only save after validation

        // Mark all ordered products as unavailable
        for (Product product : cart.getItems()) {
            product.setAvailable(false);
            productRepository.save(product);
        }

        // Clear the cart after order is successfully placed
        cart.getItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        shoppingCartRepository.save(cart);

        return savedOrder; // Return only successfully processed orders
    }

    public List<Orders> getUserOrders(Long userId) {
        return orderRepository.findByUserUserID(userId);
    }
}
