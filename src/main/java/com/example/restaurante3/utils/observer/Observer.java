package com.example.restaurante3.utils.observer;

import com.example.restaurante3.utils.event.Event;

public interface Observer<E extends Event> {
    void update(E e);
}
