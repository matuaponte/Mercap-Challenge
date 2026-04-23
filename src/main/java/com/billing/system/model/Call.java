package com.billing.system.model;

import java.time.LocalDateTime;

public abstract class Call {
    LocalDateTime dateTime;
    Integer minutes;

    public Call(LocalDateTime dateTime, Integer minutes) {
        this.dateTime = dateTime;
        this.minutes = minutes;
    }

    abstract Double calculateCost();
}
