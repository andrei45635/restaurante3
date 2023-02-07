package com.example.restaurante2.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Order extends Entity<Integer>{
    private int tableID;
    private List<Integer> menuItems;
    private LocalDateTime date;
    private OrderStatus status;

    public Order(Integer integer, int tableID, List<Integer> menuItems, LocalDateTime date, OrderStatus status) {
        super(integer);
        this.tableID = tableID;
        this.menuItems = menuItems;
        this.date = date;
        this.status = status;
    }

    public int getTableID() {
        return tableID;
    }

    public List<Integer> getMenuItems() {
        return menuItems;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
