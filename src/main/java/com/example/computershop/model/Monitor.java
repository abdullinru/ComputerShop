package com.example.computershop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity(name = "monitors")
public class Monitor extends Product{

    private int diagonal;

    public Monitor() {
    }

    public Monitor(Integer id, int serNomer, String manufacturer, double price, int quantity, int diagonal) {
        super(id, serNomer, manufacturer, price, quantity);
        this.diagonal = diagonal;
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

        Monitor monitor = (Monitor) o;

        if (serNomer != monitor.serNomer) return false;
        if (Double.compare(monitor.price, price) != 0) return false;
        if (diagonal != monitor.diagonal) return false;
        return Objects.equals(manufacturer, monitor.manufacturer);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = serNomer;
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + diagonal;
        return result;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "id=" + id +
                ", serNomer=" + serNomer +
                ", manufacturer='" + manufacturer + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", diagonal=" + diagonal +
                '}';
    }
}
