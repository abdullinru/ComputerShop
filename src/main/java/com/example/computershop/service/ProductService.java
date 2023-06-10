package com.example.computershop.service;

import com.example.computershop.dto.ProductDto;
import com.example.computershop.model.Computer;
import com.example.computershop.model.Product;

import java.util.List;

public interface ProductService {
    Product add(ProductDto newProduct);
    Product getById(Integer id);
    List<Product> getAll();
    Product edit(Integer id, ProductDto newProduct);
}
