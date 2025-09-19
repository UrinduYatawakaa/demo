package com.example.demo.repository;
import com.example.demo.entity.Category;
import java.util.List;

public interface CategoryRepository {
	List<Category> findAll();
    Category findById(Long id);
    Category save(Category category);
    void deleteById(Long id);

}
