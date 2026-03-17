package com.springproject.simpleWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springproject.simpleWebApp.model.Product;

@Repository
public interface productrepo extends JpaRepository<Product,Integer> { // mentions  the primary key type and data class type





}
