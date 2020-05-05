package com.example.foodcall;

public class Item {
    String name;
    String price;
    int quantity;

    public Item(){

    }

    public Item(String name, String price) {
        this.name = name;
        this.price = price;
    }

    public Item(String name, String price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
