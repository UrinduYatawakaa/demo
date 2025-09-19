package com.example.demo.controller;

import com.example.demo.request.SearchRequest;
import com.example.demo.bean.ErrorBean;
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

    //change to get , change body to param
    @PostMapping("/search")
    public ResponseDTO<?> searchProducts(
            @RequestBody SearchRequest request,
            HttpServletResponse response) {

        long startTime = System.currentTimeMillis();
        logger.info("START | Search Products Controller | filters: {}", request);

        try {
            // Call service with SearchRequest bean
            SearchResponse searchResponse = searchService.searchProducts(request);

            response.setStatus(HttpServletResponse.SC_OK);
            return ResponseDTO.okList(
            		//wena wenama return kranna epa 
                    searchResponse.getProducts(),
                    searchResponse.getMeta().getTotalRecord(),
                    searchResponse.getMeta().getTotalPages(),
                    searchResponse.getMeta().getCurrentPage()
            );
        } catch (Exception e) {
            logger.error("Error while searching products", e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

           
        } finally {
            long endTime = System.currentTimeMillis();
            logger.info("END | Search Products Controller | TOTAL_RESPONSE_TIME_MS = {}", (endTime - startTime));
        }
		return null;
    }
}
