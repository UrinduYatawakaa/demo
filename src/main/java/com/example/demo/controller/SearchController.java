package com.example.demo.controller;

import com.example.demo.request.SearchRequest;
import com.example.demo.response.ResponseDTO;
import com.example.demo.response.SearchResponse;
import com.example.demo.service.SearchService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class SearchController {

    private static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public SearchResponse searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = true, defaultValue = "0") int page,
            @RequestParam(required = true, defaultValue = "10") int size,
            HttpServletResponse response) {

        long startTime = System.currentTimeMillis();
        logger.info("START | Search Products Controller | filters: name={}, category={}, status={}, quantity={}, page={}, size={}",
                name, category, status, quantity, page, size);

        try {

            // Call service
            SearchResponse searchResponse = searchService.searchProducts(name,category,status,quantity,page,size);

            response.setStatus(HttpServletResponse.SC_OK);
            return searchResponse;
        } catch (Exception e) {
            logger.error("Error while searching products", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return null; //should pass error
        } finally {
            long endTime = System.currentTimeMillis();
            logger.info("END | Search Products Controller | TOTAL_RESPONSE_TIME_MS = {}", (endTime - startTime));
        }
    }
}