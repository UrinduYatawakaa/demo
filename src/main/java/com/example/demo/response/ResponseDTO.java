package com.example.demo.response;

import lombok.Data;

import java.util.List;

import com.example.demo.bean.ErrorBean;

@Data
public class ResponseDTO<T> {
    private T data;
    private int totalRecords;
    private int totalPages;
    private int currentPage;
    private ErrorBean error;

    // Success response
    public static <T> ResponseDTO<T> okList(T data, int totalRecords, int totalPages, int currentPage) {
        ResponseDTO<T> response = new ResponseDTO<>();
        response.setData(data);
        response.setTotalRecords(totalRecords);
        response.setTotalPages(totalPages);
        response.setCurrentPage(currentPage);
        response.setError(null);
        return response;
    }

    // Error response
    public static <T> ResponseDTO<T> error(String message, String code) {
        ResponseDTO<T> response = new ResponseDTO<>();
        response.setError(new ErrorBean(code, message));
        return response;
        
    }
}
