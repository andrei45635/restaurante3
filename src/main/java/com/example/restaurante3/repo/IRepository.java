package com.example.restaurante2.repo;

import com.example.restaurante2.domain.Entity;

import java.util.List;

public interface IRepository<ID, E extends Entity<Integer>> {
    List<E> findAll();
    E save(E e);
}
