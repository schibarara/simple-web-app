package com.springproject.simple.web.app.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.springproject.simple.web.app.model.Product;
import com.springproject.simple.web.app.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void getProductsReturnsRepositoryResults() {
        List<Product> products = List.of(
                new Product(1001, "Keyboard", 45),
                new Product(1002, "Webcam", 70));
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getProducts();

        assertThat(result).containsExactlyElementsOf(products);
    }

    @Test
    void getProductByIdReturnsStoredProduct() {
        Product product = new Product(1001, "Keyboard", 45);
        when(productRepository.findById(1001)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1001);

        assertThat(result).isSameAs(product);
    }

    @Test
    void getProductByIdThrowsNotFoundWhenMissing() {
        when(productRepository.findById(9999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.getProductById(9999))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(ex -> {
                    ResponseStatusException exception = (ResponseStatusException) ex;
                    assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
                    assertThat(exception.getReason()).isEqualTo("Product 9999 was not found");
                });
    }

    @Test
    void saveProductDelegatesToRepository() {
        Product product = new Product(2001, "USB Hub", 35);
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.saveProduct(product);

        assertThat(result).isSameAs(product);
    }

    @Test
    void updateProductUsesPathIdBeforeSaving() {
        Product update = new Product(9999, "Mechanical Keyboard", 60);
        Product saved = new Product(1001, "Mechanical Keyboard", 60);
        when(productRepository.existsById(1001)).thenReturn(true);
        when(productRepository.save(update)).thenReturn(saved);

        Product result = productService.updateProduct(1001, update);

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productCaptor.capture());
        assertThat(productCaptor.getValue().getId()).isEqualTo(1001);
        assertThat(result).isSameAs(saved);
    }

    @Test
    void updateProductThrowsNotFoundWhenMissing() {
        Product update = new Product(9999, "Missing Product", 10);
        when(productRepository.existsById(9999)).thenReturn(false);

        assertThatThrownBy(() -> productService.updateProduct(9999, update))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(ex -> {
                    ResponseStatusException exception = (ResponseStatusException) ex;
                    assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
                    assertThat(exception.getReason()).isEqualTo("Product 9999 was not found");
                });
        verify(productRepository, never()).save(update);
    }

    @Test
    void deleteProductDeletesExistingProduct() {
        when(productRepository.existsById(1001)).thenReturn(true);

        productService.deleteProduct(1001);

        verify(productRepository).deleteById(1001);
    }

    @Test
    void deleteProductThrowsNotFoundWhenMissing() {
        when(productRepository.existsById(9999)).thenReturn(false);

        assertThatThrownBy(() -> productService.deleteProduct(9999))
                .isInstanceOf(ResponseStatusException.class)
                .satisfies(ex -> {
                    ResponseStatusException exception = (ResponseStatusException) ex;
                    assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
                    assertThat(exception.getReason()).isEqualTo("Product 9999 was not found");
                });
        verify(productRepository, never()).deleteById(9999);
    }
}
