package com.example.demo.response;

import com.example.demo.bean.ErrorBean;
import com.example.demo.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class SearchResponse {
    private List<Product> products;
    private Meta meta;
    private ErrorBean error;  // add error here

    @Data
    public static class Meta {
        private int totalRecord;
        private int totalPages;
        private int currentPage;
    }
}
