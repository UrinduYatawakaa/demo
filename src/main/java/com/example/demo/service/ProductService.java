package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    // Constructor injection (preferred over @Autowired)
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public List<Product> getAllProducts() {
        long startTime = System.currentTimeMillis();
        logger.info("START | Fetching all products");

        List<Product> products = productRepository.findAll();

        long endTime = System.currentTimeMillis();
        logger.info("END | Fetching all products {} | TOTAL_RESPONSE_TIME_MILSEC: ", (endTime - startTime));
        return products;
    }

    // Get product by ID
    public Product getProductById(Long id) {
        long startTime = System.currentTimeMillis();
        logger.info("START | Fetching product by ID: {}", id);

        Product product = productRepository.findById(id);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
               

        long endTime = System.currentTimeMillis();
        logger.info("END | Fetching product by ID: {} | TOTAL_RESPONSE_TIME_MILSEC: {}", id, (endTime - startTime));
        return product;
    }

    // Add new product
    public Product createProduct(Product product) {
        long startTime = System.currentTimeMillis();
        logger.info("START | Creating product: {}", product);

        Product savedProduct = productRepository.save(product);

        long endTime = System.currentTimeMillis();
        logger.info("END | Creating product: {} | TOTAL_RESPONSE_TIME_MILSEC: {}", product, (endTime - startTime));
        return savedProduct;
    }

    // Update product
    public Product updateProduct(Long id, Product productDetails) {
        long startTime = System.currentTimeMillis();
        logger.info("START | Updating product ID: {} | New Details: {}", id, productDetails);

        Product product = productRepository.findById(id);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }

        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());
        product.setStatus(productDetails.getStatus());


        Product updatedProduct = productRepository.save(product);

        long endTime = System.currentTimeMillis();
        logger.info("END | Updating product ID: {} | TOTAL_RESPONSE_TIME_MILSEC: {}", id, (endTime - startTime));
        return updatedProduct;
    }

    // Delete product
    public void deleteProduct(Long id) {
        long startTime = System.currentTimeMillis();
        logger.info("START | Deleting product ID: {}", id);

        Product product = productRepository.findById(id);
        if (product == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productRepository.deleteById(id);

        long endTime = System.currentTimeMillis();
        logger.info("END | Deleting product ID: {} | TOTAL_RESPONSE_TIME_MILSEC: {}", id, (endTime - startTime));
    }
    
    // Filter by status
    public List<Product> filterByStatus(Integer status) {
        logger.info("Fetching products with status: {}", status);
        return productRepository.findByStatus(status);
    }

}
