package com.example.demodatajpa.web.controller;

import com.example.demodatajpa.entity.Product;
import com.example.demodatajpa.entity.Shop;
import com.example.demodatajpa.service.ProductService;
import com.example.demodatajpa.validation.ProductExists;
import com.example.demodatajpa.web.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<ProductResponse> create(@Valid @RequestBody CreateProductRequest request){
        Product product = productService.create(request);
        ProductResponse response = ProductResponse.builder()
                .shopResponse(toResponse(product.getShop())).build();
        BeanUtils.copyProperties(product, response);
        return Response.<ProductResponse>builder()
                .status(HttpStatus.OK.value())
                .data(toResponse(product))
                .build();
    }

    @GetMapping(path = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<ProductResponse> findById(@ProductExists @PathVariable String id){
        Product product = productService.findById(id);
        return Response.<ProductResponse>builder()
                .status(HttpStatus.OK.value())
                .data(toResponse(product))
                .build();
    }

    @PutMapping(path = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<ProductResponse> update(@ProductExists @PathVariable String id, @Valid @RequestBody UpdateProductRequest request){
        Product product = productService.update(id, request);
        return Response.<ProductResponse>builder()
                .status(HttpStatus.OK.value())
                .data(toResponse(product))
                .build();
    }

    @DeleteMapping(path = "/products/{id}")
    public Response<Boolean> deleteById(@ProductExists @PathVariable String id){
        productService.deleteById(id);
        return Response.<Boolean>builder()
                .status(HttpStatus.OK.value())
                .data(true)
                .build();
    }

    @PostMapping(path = "/products/filter", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<List<ProductResponse>> filter(@RequestBody FilterProductRequest request){
        Page<Product> productPage = productService.filter(request);
        List<ProductResponse> productResponseList = productPage.map(this::toResponse).getContent();
        return Response.<List<ProductResponse>>builder()
                .status(HttpStatus.OK.value())
                .data(productResponseList)
                .pagination(toResponse(productPage))
                .build();
    }

    private ProductResponse toResponse(Product product){
        ProductResponse productResponse = ProductResponse.builder().build();
        BeanUtils.copyProperties(product, productResponse);
        return productResponse;
    }

    private ShopResponse toResponse(Shop shop){
        ShopResponse shopResponse = ShopResponse.builder().build();
        BeanUtils.copyProperties(shop, shopResponse);
        return shopResponse;
    }

    private Response.Pagination toResponse(Page<?> page){
        return Response.Pagination.builder()
                .page(page.getNumber())
                .size((long) page.getSize())
                .totalItems(page.getTotalElements())
                .build();
    }

}
