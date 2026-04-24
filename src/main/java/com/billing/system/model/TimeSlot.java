package com.billing.system.model;

import java.time.LocalTime;

public class TimeSlot {
    private LocalTime since;
    private LocalTime until;
    private Double pricePerMinute;

    public TimeSlot(LocalTime since, LocalTime until, Double pricePerMinute) {
        this.since = since;
        this.until = until;
        this.pricePerMinute = pricePerMinute;
    }

    //Getters and Setters

    public LocalTime getSince() {
        return since;
    }

    public LocalTime getUntil() {
        return until;
    }

    public Double getPricePerMinute() {
        return pricePerMinute;
    }

    public void setSince(LocalTime since) {
        this.since = since;
    }

    public void setUntil(LocalTime until) {
        this.until = until;
    }

    public void setPricePerMinute(Double pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }
}
