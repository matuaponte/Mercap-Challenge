package com.billing.system.model;

import java.time.LocalTime;

public class TimeSlot {
    LocalTime since;
    LocalTime until;
    Double pricePerMinute;

    public TimeSlot(LocalTime since, LocalTime until, Double pricePerMinute) {
        this.since = since;
        this.until = until;
        this.pricePerMinute = pricePerMinute;
    }
}
