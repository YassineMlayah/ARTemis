package com.artemis.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.artemis.demo.model.User;
import com.artemis.demo.model.enums.Role;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {
    
    private final HttpSession session;

    public AuthController(HttpSession session) {
        this.session = session;
    }

    @GetMapping("/")
    public String home() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup.html";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact.html";
    }

    @GetMapping("/aboutus")
    public String aboutus() {
        return "aboutus.html";
    }

    @GetMapping("/role")
    public String role() {
        return "page.html";
    }

    @GetMapping("/artist-signup")
    public String artist() {
        return "artiste.html";
    }

    @GetMapping("/client-signup")
    public String client() {
        return "client.html";
    }

    @GetMapping("/add-article")
    public String article() {
        return "article.html";
    }

    @GetMapping("/profile")
    public String profile() {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        if (((User) session.getAttribute("user")).getRole() == Role.ARTIST) {
            return "update.html";
        }
        return "update-client.html";
    }

/*
    @GetMapping("/sculpture")
    public String sculpture() {
        return "sculpture.html";
    }

    @GetMapping("/painting")
    public String painting() {
        return "painting.html";
    }

    @GetMapping("/digitalart")
    public String digitalart() {
        return "digitalart.html";
    }

    @GetMapping("/sketching")
    public String sketching() {
        return "sketching.html";
    }

    @GetMapping("/photography")
    public String photography() {
        return "photography.html";
    }
*/

    @GetMapping("/cart")
    public String shoppingcart() {
        return "cart.html";
    }

    @GetMapping("/products")
    public String products() {
        return "all_products.html";
    }
}