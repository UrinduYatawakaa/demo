package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private CategoryRepositoryCrud categoryCrudRepository;

    @Override
    public List<Category> findAll() {
        return categoryCrudRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> category = categoryCrudRepository.findById(id);
        return category.orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryCrudRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryCrudRepository.deleteById(id);
    }
}
