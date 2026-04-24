package com.billing.system.model;

import java.time.LocalDateTime;

public abstract class Call {
    private LocalDateTime dateTime;
    private Integer minutes;

    public Call(LocalDateTime dateTime, Integer minutes) {
        this.dateTime = dateTime;
        this.minutes = minutes;
    }

    abstract public Double calculateCost();

    //Getters and Setters

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }
}
