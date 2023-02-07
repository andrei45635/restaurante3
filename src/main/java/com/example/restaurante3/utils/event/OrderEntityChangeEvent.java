package com.example.restaurante3.utils.event;

import com.example.restaurante3.domain.Order;

public class OrderEntityChangeEvent implements Event{
    private ChangeTypeEvent type;
    private Order data;
    private Order new_data;

    public OrderEntityChangeEvent(ChangeTypeEvent type, Order new_data) {
        this.type = type;
        this.new_data = new_data;
    }

    public ChangeTypeEvent getType() {
        return type;
    }

    public Order getData() {
        return data;
    }

    public Order getNew_data() {
        return new_data;
    }
}
