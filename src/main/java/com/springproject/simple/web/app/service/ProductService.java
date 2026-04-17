package com.springproject.simple.web.app.service;

import com.springproject.simple.web.app.model.Product;
import com.springproject.simple.web.app.repository.ProductRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> notFound(productId));
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Integer productId, Product product) {
        if (!productRepository.existsById(productId)) {
            throw notFound(productId);
        }

        product.setId(productId);
        return productRepository.save(product);
    }

    public void deleteProduct(Integer productId) {
        if (!productRepository.existsById(productId)) {
            throw notFound(productId);
        }

        productRepository.deleteById(productId);
    }

    private ResponseStatusException notFound(Integer productId) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Product %d was not found".formatted(productId));
    }
}
