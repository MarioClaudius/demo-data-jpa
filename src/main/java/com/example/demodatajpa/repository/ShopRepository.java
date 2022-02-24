package com.example.demodatajpa.repository;

import com.example.demodatajpa.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, String> {
}
