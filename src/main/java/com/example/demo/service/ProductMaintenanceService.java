package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductMaintenanceService {

    private static final Logger logger = LoggerFactory.getLogger(ProductMaintenanceService.class);

    private final ProductRepository productRepository;

    public ProductMaintenanceService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Runs every midnight (00:00) to deactivate products with zero quantity.
     */
    @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(cron = "*/5 * * * * ?")
    public void deactivateOutOfStockProducts() {
        logger.info("START | Running scheduled job: deactivate out-of-stock products");

        List<Product> outOfStockProducts = productRepository.findByQuantity(0);
        if (outOfStockProducts.isEmpty()) {
            logger.info("No out-of-stock products found.");
            return;
        }

        outOfStockProducts.forEach(product -> {
            product.setStatus(0); // 0 = inactive
            logger.info("Deactivated product: {}", product.getName());
        });

        productRepository.saveAll(outOfStockProducts);

        logger.info("END | Deactivated {} out-of-stock products", outOfStockProducts.size());
    }
}
