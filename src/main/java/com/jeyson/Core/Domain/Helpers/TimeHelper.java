package com.jeyson.Core.Domain.Helpers;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class TimeHelper {

    public static LocalDate addBusinessDays(int daysToAdd) {
        LocalDate resultDate = LocalDate.now();
        int daysAdded = 0;

        while (daysAdded < daysToAdd) {
            resultDate = resultDate.plusDays(1);
            if (resultDate.getDayOfWeek() != DayOfWeek.SATURDAY && resultDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                daysAdded++;
            }
        }

        return resultDate;
    }
}
