package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private ProductRepositoryCrud productCrudRepository;

    @Override
    public List<Product> findAll() {
        return productCrudRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> product = productCrudRepository.findById(id);
        return product.orElse(null);
    }

    @Override
    public Product save(Product product) {
        return productCrudRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productCrudRepository.deleteById(id);
    }

    @Override
    public List<Product> findByStatus(Integer status) {
        return null;
    }

    @Override
    public Page<Product> searchProducts(String name, String categoryName, Integer status, Integer quantity, Pageable pageable) {
        return productCrudRepository.searchProducts(name, categoryName, status, quantity, pageable);
    }
}
