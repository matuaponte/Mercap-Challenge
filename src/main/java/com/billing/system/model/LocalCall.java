package com.billing.system.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalCall extends  Call {
    private TimeSlot peakTime;
    private Double offPeakPrice;
    private Double weekendPrice;

    public LocalCall(LocalDateTime dateTime, Integer minutes, TimeSlot peakTime, Double offPeakPrice, Double weekendPrice) {
        super(dateTime, minutes);
        this.peakTime = peakTime;
        this.offPeakPrice = offPeakPrice;
        this.weekendPrice = weekendPrice;
    }

    @Override
    public Double calculateCost() {
        if(isWeekend()){
            return getMinutes() * weekendPrice;
        }
        else if(isPeakTime()){
            return getMinutes() * peakTime.getPricePerMinute();
        }
        return offPeakPrice * getMinutes();
    }

    private Boolean isWeekend(){
        return switch ( getDateTime().getDayOfWeek()){
            case SATURDAY, SUNDAY -> true;
            default ->  false;
        };
    }

    private Boolean isPeakTime(){
        LocalTime time =  getDateTime().toLocalTime();
        return  !time.isBefore(peakTime.getSince()) && !time.isAfter(peakTime.getUntil());
    }

    //Getters and Setters

    public TimeSlot getPeakTime() {
        return peakTime;
    }

    public Double getOffPeakPrice() {
        return offPeakPrice;
    }

    public Double getWeekendPrice() {
        return weekendPrice;
    }

    public void setPeakTime(TimeSlot peakTime) {
        this.peakTime = peakTime;
    }

    public void setOffPeakPrice(Double offPeakPrice) {
        this.offPeakPrice = offPeakPrice;
    }

    public void setWeekendPrice(Double weekendPrice) {
        this.weekendPrice = weekendPrice;
    }
}
