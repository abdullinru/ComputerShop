package com.example.computershop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "hdd")
public class HDD extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int storage;

    public HDD(int serNomer, String manufacturer, double price, int quantity, int id, int storage) {
        super(serNomer, manufacturer, price, quantity);
        this.id = id;
        this.storage = storage;
    }

    public HDD() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        HDD hdd = (HDD) o;

        if (id != hdd.id) return false;
        return storage == hdd.storage;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + storage;
        return result;
    }
}
