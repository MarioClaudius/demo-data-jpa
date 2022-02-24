package com.example.demodatajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shops",
    indexes = {
        @Index(name = "shop_name_index",
                columnList = "shop_name")
    }
)
public class Shop {
    @Id
    @Column(name = "shop_id")
    String id;

    @Column(name = "shop_name")
    String name;

    @Version
    @Column
    Long version;
}
