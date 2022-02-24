package com.example.demodatajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products",
    indexes = {
        @Index(name = "products_product_name_shop_id_index",
            columnList = "product_name, shop_id"),
        @Index(name = "products_product_name_index",
                columnList = "product_name")
    }
)
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    String id;

    @Column(name = "product_name")
    String name;

    @Column
    BigInteger price;

    @Column
    Integer stock;

    @OneToOne
    @JoinColumn(name = "shop_id")
    Shop shop;

    @Version
    @Column
    Long version;
}
