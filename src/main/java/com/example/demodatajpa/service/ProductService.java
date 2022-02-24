package com.example.demodatajpa.service;

import com.example.demodatajpa.entity.Product;
import com.example.demodatajpa.web.model.CreateProductRequest;
import com.example.demodatajpa.web.model.FilterProductRequest;
import com.example.demodatajpa.web.model.UpdateProductRequest;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product create(CreateProductRequest request);
    Product findById(String id);
    Product update(String id, UpdateProductRequest request);
    void deleteById(String id);
    Page<Product> filter(FilterProductRequest request);
}
