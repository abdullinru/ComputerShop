package com.example.computershop.model;

import java.util.Objects;

public abstract class Product {
    protected int serNomer;
    protected String manufacturer;
    protected double price;
    protected int quantity;

    public Product() {

    }
    public Product(int serNomer, String manufacturer, double price, int quantity) {
        this.serNomer = serNomer;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (serNomer != product.serNomer) return false;
        if (Double.compare(product.price, price) != 0) return false;
        return Objects.equals(manufacturer, product.manufacturer);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = serNomer;
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
