package com.example.restaurante3.repo;

import com.example.restaurante3.domain.Entity;

import java.util.List;

public interface IRepository<ID, E extends Entity<Integer>> {
    List<E> findAll();
    E save(E e);
}
