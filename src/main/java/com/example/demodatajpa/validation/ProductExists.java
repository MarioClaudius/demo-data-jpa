package com.example.demodatajpa.validation;

import com.example.demodatajpa.validation.validator.ProductExistsByIdValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(
        validatedBy = {ProductExistsByIdValidator.class}            //bakal dibikin class tersendiri untuk handle validasi
)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductExists {
    String message() default "Product doesn't exists!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
