package com.example.demo.request;

public class SearchRequest {
	private String name;
    private String category;
    private Integer status;   // 1 = active, 0 = inactive
    private Integer quantity;
    private int page = 0;     // default page number
    private int size = 10;    // default page size

    public SearchRequest() {
    }

    public SearchRequest(String name, String category, Integer status, Integer quantity, int page, int size) {
        this.name = name;
        this.category = category;
        this.status = status;
        this.quantity = quantity;
        this.page = page;
        this.size = size;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", status=" + status +
                ", quantity=" + quantity +
                ", page=" + page +
                ", size=" + size +
                '}';
    }

}
