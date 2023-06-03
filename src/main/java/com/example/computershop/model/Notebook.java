package com.example.computershop.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "notebooks")
public class Notebook extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int serNomer;
    private String manufacturer;
    private double price;
    private int quantity;
    @Enumerated(value = EnumType.STRING)
    private NotebookSize size;

    public Notebook() {
    }
    public Notebook(Integer id, int serNomer, String manufacturer, double price, int quantity, NotebookSize size) {
        this.id = id;
        this.serNomer = serNomer;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
        this.size = size;
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

    public NotebookSize getSize() {
        return size;
    }

    public void setSize(NotebookSize size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Notebook notebook = (Notebook) o;

        if (serNomer != notebook.serNomer) return false;
        if (Double.compare(notebook.price, price) != 0) return false;
        if (!Objects.equals(manufacturer, notebook.manufacturer))
            return false;
        return size == notebook.size;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = serNomer;
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (size != null ? size.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "id=" + id +
                ", serNomer=" + serNomer +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", size=" + size +
                '}';
    }

    public void increase(int quantity) {
        this.quantity += quantity;
    }
}
