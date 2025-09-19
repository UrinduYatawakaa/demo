package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.request.SearchRequest;
import com.example.demo.bean.ErrorBean;
import com.example.demo.response.SearchResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    private static final Logger logger = LoggerFactory.getLogger(SearchService.class);

    private final ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public SearchResponse searchProducts(SearchRequest searchRequest) {
        long startTime = System.currentTimeMillis();
        logger.info("START | Search Products - filters: name={}, category={}, status={}, quantity={}, page={}, size={}", 
                     searchRequest.getName(), 
                     searchRequest.getCategory(), 
                     searchRequest.getStatus(), 
                     searchRequest.getQuantity(), 
                     searchRequest.getPage(), 
                     searchRequest.getSize());

        SearchResponse response = new SearchResponse();
        try {
            Pageable pageable = PageRequest.of(searchRequest.getPage(), searchRequest.getSize());
            Page<Product> productPage = productRepository.searchProducts(
                    searchRequest.getName(),
                    searchRequest.getCategory(),
                    searchRequest.getStatus(),
                    searchRequest.getQuantity(),
                    pageable
            );

            response.setProducts(productPage.getContent());

            SearchResponse.Meta meta = new SearchResponse.Meta();
            meta.setTotalRecord((int) productPage.getTotalElements());
            meta.setTotalPages(productPage.getTotalPages());
            meta.setCurrentPage(productPage.getNumber());
            response.setMeta(meta);
            
            if(productPage.getContent() == null) {
	            ErrorBean error = new ErrorBean();
	            error.setErrorCode(500);
	            error.setErrorMessage("Failed to search products: ");
	            response.setError(error);
            }

        } catch (Exception e) {
            logger.error("ERROR | Search Products failed - {}", e.getMessage(), e);

            
        }

        long endTime = System.currentTimeMillis();
        logger.info("END | Search Products | TOTAL_RESPONSE_TIME_MILSEC - {}", (endTime - startTime));
        return response;
    }
}
