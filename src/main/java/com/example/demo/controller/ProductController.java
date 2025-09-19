package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);


    @Autowired
    private ProductService productService;

    // GET all products
    //error handeling
    @GetMapping
    public List<Product> getAllProducts() {
    	try {
            return productService.getAllProducts();
    	}catch(Exception e){
    		logger.info("error msg {} come" + e);
    	}
		return null;
    }

    // GET product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
    	try {
    		return productService.getProductById(id);
    	}catch(Exception e){
    		logger.info("error msg {} come" + e);
    	}
		return null;
        
    }

    // POST create product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        
        try {
        	return productService.createProduct(product);
    	}catch(Exception e){
    		logger.info("error msg {} come" + e);
    	}
		return null;
    }

    // PUT update product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
        	return productService.updateProduct(id, product);
    	}catch(Exception e){
    		logger.info("error msg {} come" + e);
    	}
		return null;
    }

    // DELETE product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        
        try {
        	productService.deleteProduct(id);
            return "Product deleted with id: " + id;
    	}catch(Exception e){
    		logger.info("error msg {} come" + e);
    	}
		return null;
    }
    
    // Filter by status
    @GetMapping("/filter-by-status/{status}")
    public List<Product> filterByStatus(@PathVariable Integer status) {
    	try {
            return productService.filterByStatus(status);
    	}catch(Exception e){
    		logger.info("error msg {} come" + e);
    	}
		return null;
    }
}
