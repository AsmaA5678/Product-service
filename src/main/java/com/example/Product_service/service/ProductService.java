package com.example.Product_service.service;


import com.example.Product_service.entity.Product;
import com.example.Product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public boolean checkStock(Long productId, int quantity) {
        Optional<Product> product = productRepository.findById(productId);
        return product.isPresent() && product.get().getStock() >= quantity;
    }
}
