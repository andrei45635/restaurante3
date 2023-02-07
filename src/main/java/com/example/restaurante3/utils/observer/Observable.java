package com.example.restaurante3.utils.observer;

import com.example.restaurante3.utils.event.Event;

public interface Observable<E extends Event> {
    void addObserver(Observer<E> observer);
    void notifyObservers(E e);
}
