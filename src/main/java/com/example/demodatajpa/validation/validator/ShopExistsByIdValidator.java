package com.example.demodatajpa.validation.validator;

import com.example.demodatajpa.repository.ProductRepository;
import com.example.demodatajpa.repository.ShopRepository;
import com.example.demodatajpa.validation.ShopExists;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ShopExistsByIdValidator implements ConstraintValidator<ShopExists, String> {
    @Autowired
    ShopRepository shopRepository;

    @Override
    public boolean isValid(String id, ConstraintValidatorContext constraintValidatorContext) {
        return shopRepository.existsById(id);
    }
}
