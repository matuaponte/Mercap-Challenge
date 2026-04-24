package com.billing.system;

import com.billing.system.model.Call;
import com.billing.system.model.Customer;
import com.billing.system.model.Destination;
import com.billing.system.model.InternationalCall;
import com.billing.system.model.Invoice;
import com.billing.system.model.LocalCall;
import com.billing.system.model.NationalCall;
import com.billing.system.model.TimeSlot;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer("Martin Fernandez", "+54 11 1234-5678");

        TimeSlot peakTime = new TimeSlot(LocalTime.of(8, 0), LocalTime.of(20, 0), 0.20);
        Double offPeakPrice = 0.10;
        Double weekendPrice = 0.10;

        Destination cordoba = new Destination("Córdoba", 0.50);
        Destination madrid = new Destination("Madrid", 2.0);
        Destination usa = new Destination("Usa",20.0);


        LocalDateTime peakDate = LocalDateTime.of(2026, Month.APRIL, 15, 10, 0); // Miércoles (Horario Pico)
        LocalDateTime weekendDate = LocalDateTime.of(2026, Month.APRIL, 18, 15, 0); // Sábado (Fin de semana)
        LocalDateTime offPeakDate = LocalDateTime.of(2026, Month.APRIL, 16, 22, 0); // Jueves (Fuera de horario pico)
        LocalDateTime marchDate = LocalDateTime.of(2026, Month.MARCH, 1, 10, 0); // Marzo
        LocalDateTime juneDate = LocalDateTime.of(2026, Month.JUNE, 1, 10, 0);

        List<Call> calls = Arrays.asList(
            new LocalCall(peakDate, 15, peakTime, offPeakPrice, weekendPrice),     // 15 min * 0.20 = $3.0
            new LocalCall(weekendDate, 20, peakTime, offPeakPrice, weekendPrice),  // 20 min * 0.10 = $2.0
            new LocalCall(offPeakDate, 10, peakTime, offPeakPrice, weekendPrice),  // 10 min * 0.10 = $1.0
            new NationalCall(peakDate, 12, cordoba),                               // 12 min * 0.50 = $6.0
            new InternationalCall(peakDate, 5, madrid),                            //  5 min * 2.0 = $10.0
            new LocalCall(juneDate, 30, peakTime, offPeakPrice, weekendPrice),     // JUNIO (no se debe contabilizar en ABRIL)
            new InternationalCall(marchDate, 2, usa)                               // MARZO (no se debe contabilizar en ABRIL)
        );

        Invoice invoiceApril = new Invoice(LocalDate.of(2026, Month.APRIL, 1), calls, 100.0, customer);
        System.out.println(invoiceApril.printInvoice());

        Invoice invoiceMay = new Invoice(LocalDate.of(2026, Month.MAY, 1), calls, 100.0, customer);
        System.out.println(invoiceMay.printInvoice());

        Invoice invoiceJune = new Invoice(LocalDate.of(2026, Month.JUNE, 1), calls, 100.0, customer);
        System.out.println(invoiceJune.printInvoice());

        }
    }
