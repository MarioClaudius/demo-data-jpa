package com.example.demodatajpa.service.impl;

import com.example.demodatajpa.entity.Product;
import com.example.demodatajpa.entity.Shop;
import com.example.demodatajpa.repository.ProductRepository;
import com.example.demodatajpa.repository.ShopRepository;
import com.example.demodatajpa.service.ProductService;
import com.example.demodatajpa.web.model.CreateProductRequest;
import com.example.demodatajpa.web.model.FilterProductRequest;
import com.example.demodatajpa.web.model.ProductResponse;
import com.example.demodatajpa.web.model.UpdateProductRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShopRepository shopRepository;

    @Override
    @Transactional(rollbackFor = {Exception.class})     //tanpa anotasi ini, jika uda di-save duluan lalu error, data tetap akan kesimpan
    public Product create(CreateProductRequest request) {
        Shop shop = shopRepository.getById(request.getShopId());
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .stock(request.getStock())
                .shop(shop)
                .build();

//        productRepository.save(product);
//        if(true) throw new RuntimeException("test Rollback");       //buat tes dimana meskipun error terjadi setelah save, data tidak masuk ke database
//        return null;
        return productRepository.save(product);
    }

    @Override
    public Product findById(String id) {
        return productRepository.getById(id);
    }

    @Override
    public Product update(String id, UpdateProductRequest request) {
        Product product = productRepository.getById(id);
        BeanUtils.copyProperties(request, product);
        if(!request.getShopId().equals(product.getShop().getId())){
            Shop shop = shopRepository.getById(request.getShopId());
            product.setShop(shop);
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public Page<Product> filter(FilterProductRequest request) {
        return productRepository.findByFilter(request);
    }
}
