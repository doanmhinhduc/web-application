package com.example.webapplication.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private int id;
    private String name;
    private BigDecimal wage;

}
