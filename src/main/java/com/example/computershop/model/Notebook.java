package com.example.computershop.model;

import jakarta.persistence.*;

@Entity(name = "notebooks")
public class Notebook extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private NotebookSize size;

    public Notebook() {

    }

    public Notebook(int serNomer, String manufacturer, double price, int quantity, int id, NotebookSize size) {
        super(serNomer, manufacturer, price, quantity);
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (!super.equals(o)) return false;

        Notebook notebook = (Notebook) o;

        if (id != notebook.id) return false;
        return size == notebook.size;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id;
        result = 31 * result + (size != null ? size.hashCode() : 0);
        return result;
    }
}
