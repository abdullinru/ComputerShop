package com.example.computershop.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "computers" )
public class Computer extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int serNomer;
    private String manufacturer;
    private double price;
    private int quantity;

    @Enumerated(value = EnumType.STRING)
    private FormFactor type;

    public Computer() {

    }

    public Computer(Integer id, int serNomer, String manufacturer, double price, int quantity, FormFactor type) {
        this.id = id;
        this.serNomer = serNomer;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
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

    public FormFactor getType() {
        return type;
    }

    public void setType(FormFactor type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Computer computer = (Computer) o;

        if (serNomer != computer.serNomer) return false;
        if (Double.compare(computer.price, price) != 0) return false;
        if (!Objects.equals(manufacturer, computer.manufacturer))
            return false;
        return type == computer.type;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = serNomer;
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", serNomer=" + serNomer +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", type=" + type +
                '}';
    }

    public void increase(int quantity) {
        this.quantity += quantity;
    }
}
