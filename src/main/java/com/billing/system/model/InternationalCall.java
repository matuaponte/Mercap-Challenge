package com.billing.system.model;

import java.time.LocalDateTime;

public class InternationalCall extends Call {
    private Destination country;

    public  InternationalCall(LocalDateTime dateTime, Integer minutes, Destination country) {
        super(dateTime, minutes);
        this.country = country;
    }


    @Override
    public Double calculateCost() {
        return getMinutes() * country.getPricePerMinute();
    }

    //Getters and Setters

    public void setCountry(Destination country) {
        this.country = country;
    }

    public Destination getCountry() {
        return country;
    }
}
