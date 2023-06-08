package com.example.computershop.dto;

import lombok.Data;

@Data
public abstract class ProductDto {
    protected int serNomer;
    protected String manufacturer;
    protected double price;
    protected int quantity;
}
