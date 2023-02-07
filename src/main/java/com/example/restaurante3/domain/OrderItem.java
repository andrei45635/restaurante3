package com.example.restaurante3.domain;

public class OrderItem extends Entity<Integer> {
    private int orderID;
    private int menuItemID;
    private int quantity;

    public OrderItem(int id, int orderID, int menuItemID, int quantity) {
        super(id);
        this.orderID = orderID;
        this.menuItemID = menuItemID;
        this.quantity = quantity;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getMenuItemID() {
        return menuItemID;
    }

    public int getQuantity() {
        return quantity;
    }
}
