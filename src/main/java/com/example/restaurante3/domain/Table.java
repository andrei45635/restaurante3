package com.example.restaurante3.domain;

public class Table extends Entity<Integer> {
    public Table(Integer integer) {
        super(integer);
    }

    @Override
    public String toString() {
        return "Table{}";
    }
}
