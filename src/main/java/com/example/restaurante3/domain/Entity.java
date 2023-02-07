package com.example.restaurante2.domain;

import java.io.Serializable;

public class Entity<ID> implements Serializable {
    private final long serialVersionUID = 1234432342L;

    protected ID id;

    public Entity(ID id) {
        this.id = id;
    }

    public ID getId() {
        return id;
    }
}
