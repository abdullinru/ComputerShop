package com.example.computershop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public abstract class Product {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     protected Integer id;

     protected int serNomer;
     protected String manufacturer;
     protected double price;
     protected int quantity;

     public Product() {

     }

     public Product(Integer id, int serNomer, String manufacturer, double price, int quantity) {
          this.id = id;
          this.serNomer = serNomer;
          this.manufacturer = manufacturer;
          this.price = price;
          this.quantity = quantity;
     }

     public Integer getId() {
          return id;
     }

     public void setId(Integer id) {
          this.id = id;
     }

     public int getSerNomer() {
          return serNomer;
     }

     public void setSerNomer(int serNomer) {
          this.serNomer = serNomer;
     }

     public String getManufacturer() {
          return manufacturer;
     }

     public void setManufacturer(String manufacturer) {
          this.manufacturer = manufacturer;
     }

     public double getPrice() {
          return price;
     }

     public void setPrice(double price) {
          this.price = price;
     }

     public int getQuantity() {
          return quantity;
     }

     public void setQuantity(int quantity) {
          this.quantity = quantity;
     }
     public void increase(int quantity) {
          this.quantity += quantity;
     }
}
