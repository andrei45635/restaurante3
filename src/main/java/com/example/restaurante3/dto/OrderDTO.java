package com.example.restaurante3.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private int tableID;
    private LocalDateTime date;
    private String food;

    public OrderDTO(int tableID, LocalDateTime date, String food) {
        this.tableID = tableID;
        this.date = date;
        this.food = food;
    }

    public int getTableID() {
        return tableID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getFood() {
        return food;
    }
}
