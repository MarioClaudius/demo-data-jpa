package com.example.demodatajpa.repository;

import com.example.demodatajpa.entity.Product;
import com.example.demodatajpa.web.model.FilterProductRequest;
import org.springframework.data.domain.Page;

public interface ProductCustomRepository {
    Page<Product> findByFilter(FilterProductRequest request);
}
