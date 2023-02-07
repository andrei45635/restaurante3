package com.example.restaurante2.domain;

public class MenuItem extends Entity<Integer>{
    private String category;
    private String item;
    private float price;
    private String currency;

    public MenuItem(Integer integer, String category, String item, float price, String currency) {
        super(integer);
        this.category = category;
        this.item = item;
        this.price = price;
        this.currency = currency;
    }

    public String getCategory() {
        return category;
    }

    public String getItem() {
        return item;
    }

    public float getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }
}
