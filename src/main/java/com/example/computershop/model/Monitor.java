package com.example.computershop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "monitors")
public class Monitor extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int diagonal;

    public Monitor() {
    }

    public Monitor(int serNomer, String manufacturer, double price, int quantity, int id, int diagonal) {
        super(serNomer, manufacturer, price, quantity);
        this.id = id;
        this.diagonal = diagonal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(int diagonal) {
        this.diagonal = diagonal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Monitor monitor = (Monitor) o;

        if (id != monitor.id) return false;
        return diagonal == monitor.diagonal;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + diagonal;
        return result;
    }
}
