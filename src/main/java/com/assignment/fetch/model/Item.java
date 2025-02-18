package com.assignment.fetch.model;

public class Item {

    private String shortDescription;

    private String price;

    public Item() {
    }

    public Item(String shortDescription, String price) {
        this.shortDescription = shortDescription;
        this.price = price;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "shortDescription='" + shortDescription + '\'' +
                ", price=" + price +
                '}';
    }
}
