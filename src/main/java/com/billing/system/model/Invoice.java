package com.billing.system.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class Invoice {
    private Customer customer;
    private LocalDate generationDate;
    private LocalDate period;
    private List<Call> callDetails;
    private Double basicFee;
    private Double totalAmount;

    public Invoice(LocalDate period, List<Call> callDetails, Double basicFee, Customer customer) {
        this.generationDate = LocalDate.now();
        this.period = period;
        this.callDetails = callDetails;
        this.basicFee = basicFee;
        this.customer = customer;
    }

    public Double totalCost(){
        return callDetails.stream()
            .filter(this::checkMonthYear)
            .mapToDouble(Call::calculateCost)
            .sum() + basicFee;
    }

    private Double localCost(){
        return callDetails.stream()
            .filter(c -> c instanceof LocalCall && this.checkMonthYear(c))
            .mapToDouble(Call::calculateCost)
            .sum();
    }

    private Double internationalCost(){
        return callDetails.stream()
            .filter(c -> c instanceof InternationalCall && this.checkMonthYear(c))
            .mapToDouble(Call::calculateCost)
            .sum();
    }

    private Double nationalCost(){
        return callDetails.stream()
            .filter(c -> c instanceof NationalCall && this.checkMonthYear(c))
            .mapToDouble(Call::calculateCost)
            .sum();
    }

    private boolean checkMonthYear(Call c){
        return c.getDateTime().getMonth() == period.getMonth() && c.getDateTime().getYear() == period.getYear();
    }

    public String printInvoice(){
        this.totalAmount = this.totalCost();
        Double localCallAmount = this.localCost();
        Double nationalCallAmount = this.nationalCost();
        Double internationalCallAmount = this.internationalCost();
        String month = period.getMonth().toString();

        StringBuilder sb = new StringBuilder();

        sb.append("-------------------------------------\n");
        sb.append("Invoince of Calls\n");
        sb.append("-------------------------------------\n");
        sb.append("Costumer: ").append(customer.getFullName()).append("\n");
        sb.append("Phone Number: ").append(customer.getPhoneNumber()).append("\n");
        sb.append("Period: ").append(month).append("\n");
        sb.append("Emision Date: ").append(generationDate).append("\n");
        sb.append("-------------------------------------\n");
        sb.append("         Details of Amount           \n");
        sb.append("-------------------------------------\n");
        sb.append("Basic Fee: ").append(basicFee).append("\n");
        sb.append("Local Calls: ").append(localCallAmount).append("\n");
        sb.append("National Calls: ").append(nationalCallAmount).append("\n");
        sb.append("International Calls: ").append(internationalCallAmount).append("\n");
        sb.append("-------------------------------------\n");
        sb.append("Total Amount: ").append(totalAmount).append("\n");
        sb.append("-------------------------------------\n");

        return sb.toString();
    }

    //Getters and Setters

    public LocalDate getGenerationDate() {
        return generationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getPeriod() {
        return period;
    }

    public List<Call> getCallDetails() {
        return callDetails;
    }

    public Double getBasicFee() {
        return basicFee;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setGenerationDate(LocalDate generationDate) {
        this.generationDate = generationDate;
    }

    public void setPeriod(LocalDate period) {
        this.period = period;
    }

    public void setCallDetails(List<Call> callDetails) {
        this.callDetails = callDetails;
    }

    public void setBasicFee(Double basicFee) {
        this.basicFee = basicFee;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
