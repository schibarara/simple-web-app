package com.springproject.simple.web.app.config;

import com.springproject.simple.web.app.model.Product;
import com.springproject.simple.web.app.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedProducts(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.save(new Product(1001, "Keyboard", 45));
                productRepository.save(new Product(1002, "Webcam", 70));
            }
        };
    }
}
