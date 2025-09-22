package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.request.SearchRequest;
import com.example.demo.bean.ErrorBean;
import com.example.demo.bean.SearchRequestBean;
import com.example.demo.response.SearchResponse;

import java.util.ArrayList;
import java.util.List;

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
            if (productPage.getContent() == null) {
				ErrorBean error = new ErrorBean();
				error.setErrorCode(500);
				error.setErrorMessage("Failed to search products: ");
				response.setError(error);
				return response;
			}
            List<SearchRequestBean> bean = dataSet(productPage.getContent());
            response.setProducts(productPage.getContent());

            SearchResponse.Meta meta = new SearchResponse.Meta();
            meta.setTotalRecord((int) productPage.getTotalElements());
            meta.setTotalPages(productPage.getTotalPages());
            meta.setCurrentPage(productPage.getNumber());
            response.setMeta(meta);
            

        } catch (Exception e) {
            logger.error("ERROR | Search Products failed - {}", e.getMessage(), e);

            
        }

        long endTime = System.currentTimeMillis();
        logger.info("END | Search Products | TOTAL_RESPONSE_TIME_MILSEC - {}", (endTime - startTime));
        return response;
    }

	private List<SearchRequestBean> dataSet(List<Product> content) {
		List<SearchRequestBean> docList = new ArrayList<>();
		content.stream().forEach(doc -> {
			SearchRequestBean documentSearchBean = mapToDocBean(doc);
			docList.add(documentSearchBean);
		});
		return docList;
	}
	
	private SearchRequestBean mapToDocBean(Product product) {
		SearchRequestBean bean = new SearchRequestBean();
		bean.setName(product.getName());
		bean.setCategory(product.getCategory().getName());
		bean.setQuantity(product.getQuantity());
		bean.setPrice(product.getPrice());
		bean.setStatus(product.getStatus());
		
		return bean;
	}
}
