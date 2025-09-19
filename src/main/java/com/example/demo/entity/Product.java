package com.example.demo.entity;

import jakarta.persistence.Column;

//table annotation, column notation, package ekk athulata
//

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity        // Marks this as a database table
@Data          // Lombok generates getters, setters, toString, etc.
@Table(name="Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment id
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "price")
    private double price;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = " status")
    private int status;
    
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
