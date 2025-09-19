package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoryCrud extends JpaRepository<Category, Long> {
    // You can add custom queries here if needed, e.g.
    // List<Category> findByName(String name);
}
