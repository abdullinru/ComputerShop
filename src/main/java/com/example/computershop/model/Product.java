package com.example.computershop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
public abstract class Product {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     protected Integer id;

     protected int serNomer;
     protected String manufacturer;
     protected double price;
     protected int quantity;

     public Product(Integer id, int serNomer, String manufacturer, double price, int quantity) {
          this.id = id;
          this.serNomer = serNomer;
          this.manufacturer = manufacturer;
          this.price = price;
          this.quantity = quantity;
     }
     public void increase(int quantity) {
          this.quantity += quantity;
     }
}
