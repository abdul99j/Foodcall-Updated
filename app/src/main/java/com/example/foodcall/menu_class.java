package com.example.foodcall;

public class menu_class {

    String itemName;
    String price;
    Boolean isSelected;

    public menu_class(String itemName, String price, Boolean isSelected) {
        this.itemName = itemName;
        this.price = price;
        this.isSelected = isSelected;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}