package com.example.demodatajpa.repository;

import com.example.demodatajpa.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String>, ProductCustomRepository {
    //define query sendiri
    Product findByName(String name);
}
