package com.example.restaurante3.domain;

import java.util.Objects;

public class MenuItem extends Entity<Integer>{
    private String category;
    private String item;
    private float price;
    private String currency;
    private String priceCurrency;

    public MenuItem(Integer integer, String category, String item, float price, String currency) {
        super(integer);
        this.category = category;
        this.item = item;
        this.price = price;
        this.currency = currency;
        priceCurrency = price+currency;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        return Float.compare(menuItem.getPrice(), getPrice()) == 0 && Objects.equals(getCategory(), menuItem.getCategory()) && Objects.equals(getItem(), menuItem.getItem()) && Objects.equals(getCurrency(), menuItem.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory(), getItem(), getPrice(), getCurrency());
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "category='" + category + '\'' +
                ", item='" + item + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }
}
