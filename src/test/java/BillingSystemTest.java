package com.billing.system.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;


public class BillingSystemTest {

    private TimeSlot peakTime;
    private Customer customer;

    @BeforeEach
    public void setUp() {
        // Horario hábil de 8 a 20 hrs a 0.20
        peakTime = new TimeSlot(LocalTime.of(8, 0), LocalTime.of(20, 0), 0.20);
        customer = new Customer("Martin Fernadez", "123456789");
    }

    @Test
    public void testLocalCallPeakTime() {
        // (Día de semana, horario pico)
        LocalDateTime dateTime = LocalDateTime.of(2026, Month.MAY, 15, 10, 0);
        LocalCall call = new LocalCall(dateTime, 10, peakTime, 0.10, 0.10);

        Assertions.assertEquals(2.0, call.calculateCost(), 0.001);
    }

    @Test
    public void testLocalCallOffPeakTime() {
        //(Día de semana, fuera de horario)
        LocalDateTime dateTime = LocalDateTime.of(2026, Month.MAY, 15, 6, 0);
        LocalCall call = new LocalCall(dateTime, 10, peakTime, 0.10, 0.10);

        Assertions.assertEquals(1.0, call.calculateCost(), 0.001);
    }

    @Test
    public void testLocalCallOffPeakTimeWeekend() {
        //(Fin de semana, dentro de horario)
        LocalDateTime dateTime = LocalDateTime.of(2026, Month.APRIL, 19, 8, 0);
        LocalCall call = new LocalCall(dateTime, 10, peakTime, 0.10, 0.10);

        Assertions.assertEquals(1.0, call.calculateCost(), 0.001);
    }

    @Test
    public void testLocalCallWeekend() {
        //(Fin de semana, fuera de horario) Sábado 16 de mayo de 2026
        LocalDateTime dateTime = LocalDateTime.of(2026, Month.MAY, 16, 20, 1);
        LocalCall call = new LocalCall(dateTime, 10, peakTime, 0.10, 0.10);

        Assertions.assertEquals(1.0, call.calculateCost(), 0.001);
    }

    @Test
    public void testNationalCall() {
        LocalDateTime dateTime = LocalDateTime.of(2026, Month.MAY, 15, 10, 0);
        Destination buenosAires = new Destination("Buenos Aires", 0.50);
        NationalCall call = new NationalCall(dateTime, 10, buenosAires);

        Assertions.assertEquals(5.0, call.calculateCost(), 0.001);
    }

    @Test
    public void testInternationalCall() {
        LocalDateTime dateTime = LocalDateTime.of(2026, Month.MAY, 15, 10, 0);
        Destination usa = new Destination("USA", 1.50);
        InternationalCall call = new InternationalCall(dateTime, 10, usa);

        Assertions.assertEquals(15.0, call.calculateCost(), 0.001);
    }

    @Test
    public void testInvoiceCountsOnlyCallsInInvoiceMonth() {
        LocalDateTime mayDate = LocalDateTime.of(2026, Month.MAY, 15, 10, 0);
        LocalDateTime juneDate = LocalDateTime.of(2026, Month.JUNE, 1, 10, 0);

        Destination madrid = new Destination("Madrid", 2.0);

        List<Call> calls = new ArrayList<>();
        calls.add(new LocalCall(mayDate, 10, peakTime, 0.10, 0.10)); // esperado 2.0
        calls.add(new InternationalCall(mayDate, 5, madrid)); // esperado 10.0
        calls.add(new LocalCall(juneDate, 10, peakTime, 0.10, 0.10)); // esperado 2.0

        Invoice invoiceMay = new Invoice(LocalDate.of(2026, Month.MAY, 1), calls, 100.0, customer);
        Assertions.assertEquals(112.0, invoiceMay.totalCost(), 0.001);

        Invoice invoiceJune = new Invoice(LocalDate.of(2026, Month.JUNE, 1), calls, 100.0, customer);
        Assertions.assertEquals(102.0, invoiceJune.totalCost(), 0.001);
    }
}

