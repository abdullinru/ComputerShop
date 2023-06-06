package com.example.computershop.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Data
@NoArgsConstructor
@ToString
@Entity(name = "notebooks")
public class Notebook extends Product{
    @Enumerated(value = EnumType.STRING)
    private NotebookSize size;

    public Notebook(Integer id, int serNomer, String manufacturer, double price, int quantity, NotebookSize size) {
        super(id, serNomer, manufacturer, price, quantity);
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
}
