package com.example.computershop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Data
@NoArgsConstructor
@ToString
@Entity(name = "computers" )
public class Computer extends Product{

    @Enumerated(value = EnumType.STRING)
    private FormFactor type;

    public Computer(Integer id, int serNomer, String manufacturer, double price, int quantity, FormFactor type) {
        super(id, serNomer, manufacturer, price, quantity);
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
}
