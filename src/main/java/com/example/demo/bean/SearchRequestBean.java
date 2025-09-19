package com.example.demo.bean;

import lombok.Data;

@Data
public class SearchRequestBean {
    private String name;
    private String category;
    private Integer status;   // 1=active, 0=inactive
    private Integer quantity;
    private int page;
    private int size;
}
