package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryCrud extends JpaRepository<Product, Long> {

    @Query("FROM Product p WHERE " +
           "(:name IS NULL OR p.name LIKE CONCAT('%', :name, '%')) AND " +
           "(:categoryName IS NULL OR p.category.name = :categoryName) AND " +
           "(:status IS NULL OR p.status = :status) AND " +
           "(:quantity IS NULL OR p.quantity = :quantity)")
    Page<Product> searchProducts(String name, String categoryName, Integer status, Integer quantity, Pageable pageable);
}
