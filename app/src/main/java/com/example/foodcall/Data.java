package com.example.foodcall;

public class Data {
    private Integer image_Recycle;
    private String image_Name;
    private String image_Price;

    public Data() {

    }

    public Data(Integer image_Recycle, String image_Name, String image_Price) {
        this.image_Recycle = image_Recycle;
        this.image_Name = image_Name;
        this.image_Price = image_Price;
    }

    public Integer getImage_Recycle() {
        return image_Recycle;
    }

    public void setImage_Recycle(Integer image_Recycle) {
        this.image_Recycle = image_Recycle;
    }

    public String getImage_Name() {
        return image_Name;
    }

    public void setImage_Name(String image_Name) {
        this.image_Name = image_Name;
    }

    public String getImage_Price() {
        return image_Price;
    }

    public void setImage_Price(String image_Price) {
        this.image_Price = image_Price;
    }
}
