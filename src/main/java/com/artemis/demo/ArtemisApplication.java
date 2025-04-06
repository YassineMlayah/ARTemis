package com.artemis.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.artemis.demo.model")
public class ArtemisApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArtemisApplication.class, args);
    }
}
