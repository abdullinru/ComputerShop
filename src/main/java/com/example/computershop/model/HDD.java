package com.example.computershop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Data
@NoArgsConstructor
@ToString
@Entity(name = "hdd")
public class HDD extends Product{

    private int storage;

    public HDD(Integer id, int serNomer, String manufacturer, double price, int quantity, int storage) {
        super(id, serNomer, manufacturer, price, quantity);
        this.storage = storage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HDD hdd = (HDD) o;

        if (serNomer != hdd.serNomer) return false;
        if (Double.compare(hdd.price, price) != 0) return false;
        if (storage != hdd.storage) return false;
        return Objects.equals(manufacturer, hdd.manufacturer);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = serNomer;
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + storage;
        return result;
    }
}
