package com.example.foodcall;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    String restaurantName;
    Date orderDate;
    List<Item> items=new ArrayList<>();

    public Order(String restaurantName, Date orderDate, List<Item> items) {
        this.restaurantName = restaurantName;
        this.orderDate = orderDate;
        this.items = items;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
