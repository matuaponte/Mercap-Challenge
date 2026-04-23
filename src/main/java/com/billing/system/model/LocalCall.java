package com.billing.system.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalCall extends  Call {
    TimeSlot peakTime;
    Double offPeakPrice;
    Double weekendPrice;

    public LocalCall(LocalDateTime dateTime, Integer minutes, TimeSlot peakTime, Double offPeakPrice, Double weekendPrice) {
        super(dateTime, minutes);
        this.peakTime = peakTime;
        this.offPeakPrice = offPeakPrice;
        this.weekendPrice = weekendPrice;
    }

    @Override
    Double calculateCost() {
        if(isWeekend()){
            return minutes * weekendPrice;
        }
        else if(isPeakTime()){
            return minutes * peakTime.pricePerMinute;
        }
        return offPeakPrice * minutes;
    }

    private Boolean isWeekend(){
        return switch (dateTime.getDayOfWeek()){
            case SATURDAY, SUNDAY -> true;
            default ->  false;
        };
    }

    private Boolean isPeakTime(){
        LocalTime time = dateTime.toLocalTime();
        return  !time.isBefore(peakTime.since) && !time.isAfter(peakTime.until);
    }
}
