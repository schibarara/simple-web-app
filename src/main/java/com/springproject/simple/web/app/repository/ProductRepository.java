package com.springproject.simple.web.app.repository;

import com.springproject.simple.web.app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
