package com.billing.system.model;

import java.time.LocalDateTime;

public class NationalCall extends Call{
    private Destination location;

        public NationalCall(LocalDateTime dateTime, Integer minutes, Destination location) {
        super(dateTime, minutes);
        this.location = location;
    }

    @Override
    public Double calculateCost() {
        return getMinutes() * location.getPricePerMinute();
    }

    //Getters and Setters

    public Destination getLocation() {
        return location;
    }

    public void setLocation(Destination location) {
        this.location = location;
    }
}
