package com.example.demodatajpa.validation.validator;

import com.example.demodatajpa.repository.ProductRepository;
import com.example.demodatajpa.validation.ProductExists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProductExistsByIdValidator implements ConstraintValidator<ProductExists, String> {
    @Autowired
    ProductRepository productRepository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return productRepository.existsById(id);
    }
}
