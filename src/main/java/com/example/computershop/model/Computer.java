package com.example.computershop.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "computers" )
public class Computer extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private FormFactor type;

    public Computer() {
        super();
    }

    public Computer(int serNomer, String manufacturer, double price, int quantity, int id, FormFactor type) {
        super(serNomer, manufacturer, price, quantity);
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (!super.equals(o)) return false;

        Computer computer = (Computer) o;

        if (id != computer.id) return false;
        return type == computer.type;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
