package com.demoApp.tools.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CalenderService {
    public List<LocalDate> addHolidays()
    {
        List<LocalDate> holidays = new ArrayList<>();
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yy");
        String[] holiday = { "07/04/24", "09/01/24"};
        for(String day : holiday)
        {
            LocalDate dateparsed = LocalDate.parse(day,formatters);
            //  LocalDate holidayData = LocalDate.parse(dateparsed, formatters);
            holidays.add(dateparsed);
        }
        return holidays;
    }

    public  int countBusinessDaysBetween(final LocalDate startDate,
                                               final LocalDate endDate,
                                               final List<LocalDate> holidays) {

     /*   LocalDate endDateInclusive = endDate.plusDays(1);
        final DayOfWeek startW = startDate.getDayOfWeek();
        final DayOfWeek endW = endDateInclusive.getDayOfWeek();
        // Predicate 1: Is a given date is a holiday
        final long days = ChronoUnit.DAYS.between(startDate, endDateInclusive);
        final long daysWithoutWeekends = days - 2 * ((days + startW.getValue())) / 7;



        //adjust for starting and ending on a Sunday:
        return (int)
                (  daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0) ) - holidays ;
*/

        // Predicate 1: Is a given date is a holiday
     LocalDate endDateInclusive = endDate.plusDays(1);
        Predicate<LocalDate> isHoliday = holidays::contains;

        // Predicate 2: Is a given date is a weekday
        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        // Get all days between two dates
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDateInclusive);

        // Iterate over stream of all dates and check each day against any weekday or
        // holiday
        return Stream.iterate(startDate, date -> date.plusDays(1))
                .limit(daysBetween)
                .filter(isHoliday.or(isWeekend).negate())
                .toList().size();

    }
    public  int countWeekendsBetween(final LocalDate startDate,
                                           final LocalDate endDate
                                          ) {



        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;
        // Iterate over stream of all dates and check each day against any weekday or holiday

        return startDate.datesUntil(endDate)
                .filter(isWeekend)
                .toList().size();
    }

    public  int countHolidaysBetween(final LocalDate startDate,
                                           final LocalDate endDate,
                                           final List<LocalDate> holidays) {


        Predicate<LocalDate> isHoliday = holidays::contains;
        // Iterate over stream of all dates and check each day against any weekday or holiday

        List<LocalDate> holidayList = startDate.datesUntil(endDate)
                .filter(isHoliday)
                .toList();

        return holidayList.size();
    }

    public LocalDate calculateDueDate(Integer rentalDays, LocalDate checkoutDate) {
        LocalDate date = checkoutDate;
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM/dd/yy");
        String text = date.format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);

        System.out.println("date: " + date); // date: 2016-09-25
        System.out.println("Text format " + text); // Text format 25/09/2016
        System.out.println("parsedDate: " + parsedDate); // parsedDate: 2016-09-25
        return parsedDate.plusDays(rentalDays);
    }
}
