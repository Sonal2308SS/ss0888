package com.demoApp.tools.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class CalenderServiceTest {
    private final CalenderService calenderService = new CalenderService();

    @Test
    public void testDueDate() {
        LocalDate result = calenderService.calculateDueDate(10, LocalDate.parse("2024-06-04"));
        assertEquals(LocalDate.parse("2024-06-14"), result, "Adding RentalDays to CheckoutDate: DueDate");
    }

    @Test
    public void testBusinessDays_WithoutHoliday() {
        int result = calenderService.countBusinessDaysBetween(LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-31"), Collections.singletonList(LocalDate.parse("2024-07-01")));
        assertEquals(23, result, "BuisnessDays total");

    }
    @Test
    public void testBusinessDays_WithHoliday() {
        int result = calenderService.countBusinessDaysBetween(LocalDate.parse("2024-07-01"),LocalDate.parse("2024-07-31"), Collections.singletonList(LocalDate.parse("2024-07-04")));
        assertEquals(22, result, "BuisnessDays total except holiday");
    }

    @Test
    public void testBusinessDays_WithHoliday_SS() {
        int result = calenderService.countBusinessDaysBetween(LocalDate.parse("2024-09-01"),LocalDate.parse("2024-09-30"),Collections.singletonList(LocalDate.parse("2024-09-01")));
        assertEquals(21, result, "BuisnessDays total except holiday");
    }


    @Test
    public void Weekends() {
        int result = calenderService.countWeekendsBetween(LocalDate.parse("2024-07-01"),LocalDate.parse("2024-07-31"));
        assertEquals(8, result, "Weekends total");
    }
}
