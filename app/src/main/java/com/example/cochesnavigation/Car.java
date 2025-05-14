package com.example.cochesnavigation;


public class Car {
    private final String name;
    private final String price;
    private final String year;
    private final int imageResId;

    public Car(String name, String price, String year, int imageResId) {
        this.name = name;
        this.price = price;
        this.year = year;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getYear() {
        return year;
    }

    public int getImageResId() {
        return imageResId;
    }
}
