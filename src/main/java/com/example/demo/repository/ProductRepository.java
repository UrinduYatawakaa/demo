package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    List<Product> findByStatus(Integer status);
    
    List<Product> findByQuantity(int quantity);

    Page<Product> searchProducts(String name, String categoryName, Integer status, Integer quantity, Pageable pageable);

	void saveAll(List<Product> outOfStockProducts);
}
