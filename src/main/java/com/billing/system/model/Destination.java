package com.billing.system.model;

public class Destination {
    private String name;
    private Double pricePerMinute;

    public Destination(String name, Double pricePerMinute) {
        this.name = name;
        this.pricePerMinute = pricePerMinute;
    }

    //Getters and Setters

    public String getName() {
        return name;
    }

    public Double getPricePerMinute() {
        return pricePerMinute;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPricePerMinute(Double pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }
}
