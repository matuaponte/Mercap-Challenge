
import com.billing.system.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test {

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
        // Lunes 15 de mayo de 2023 a las 10:00 (Día de semana, horario pico)
        LocalDateTime dateTime = LocalDateTime.of(2023, Month.MAY, 15, 10, 0);
        LocalCall call = new LocalCall(dateTime, 10, peakTime, 0.10, 0.10);

        // 10 minutos * 0.20 = 2.0
        assertEquals(2.0, call.calculateCost(), 0.001);
    }

    @Test
    public void testLocalCallOffPeakTime() {
        // Lunes 15 de mayo de 2023 a las 06:00 (Día de semana, fuera de horario)
        LocalDateTime dateTime = LocalDateTime.of(2023, Month.MAY, 15, 6, 0);
        LocalCall call = new LocalCall(dateTime, 10, peakTime, 0.10, 0.10);

        // 10 minutos * 0.10 = 1.0
        assertEquals(1.0, call.calculateCost(), 0.001);
    }

    @Test
    public void testLocalCallWeekend() {
        // Sábado 20 de mayo de 2023 a las 15:00 (Fin de semana)
        LocalDateTime dateTime = LocalDateTime.of(2023, Month.MAY, 20, 15, 0);
        LocalCall call = new LocalCall(dateTime, 10, peakTime, 0.10, 0.10);

        // 10 minutos * 0.10 = 1.0
        assertEquals(1.0, call.calculateCost(), 0.001);
    }

    @Test
    public void testNationalCall() {
        LocalDateTime dateTime = LocalDateTime.of(2023, Month.MAY, 15, 10, 0);
        Destination buenosAires = new Destination("Buenos Aires", 0.50);
        NationalCall call = new NationalCall(dateTime, 10, buenosAires);

        // 10 minutos * 0.50 = 5.0
        assertEquals(5.0, call.calculateCost(), 0.001);
    }

    @Test
    public void testInternationalCall() {
        LocalDateTime dateTime = LocalDateTime.of(2023, Month.MAY, 15, 10, 0);
        Destination usa = new Destination("USA", 1.50);
        InternationalCall call = new InternationalCall(dateTime, 10, usa);

        // 10 minutos * 1.50 = 15.0
        assertEquals(15.0, call.calculateCost(), 0.001);
    }

    @Test
    public void testInvoiceTotalCalculation() {
        LocalDateTime peakDate = LocalDateTime.of(2023, Month.MAY, 15, 10, 0); // 10 min = $2.0 (Local)
        LocalDateTime weekendDate = LocalDateTime.of(2023, Month.MAY, 20, 15, 0); // 10 min = $1.0 (Local)
        Destination madrid = new Destination("Madrid", 2.0); // 5 min = $10.0 (Internacional)

        List<Call> calls = new ArrayList<>();
        calls.add(new LocalCall(peakDate, 10, peakTime, 0.10, 0.10));
        calls.add(new LocalCall(weekendDate, 10, peakTime, 0.10, 0.10));
        calls.add(new InternationalCall(peakDate, 5, madrid));

        // Abono básico: $100.0
        Invoice invoice = new Invoice(Month.MAY, calls, 100.0, customer);

        // Total = 100.0 (abono) + 2.0 + 1.0 + 10.0 = 113.0
        assertEquals(113.0, invoice.totalCost(), 0.001);
    }
}

