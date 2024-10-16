package com.jfb.lecture5.utils;

import com.jfb.lecture5.model.BusTicket;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class StartDateValidatorUtil {
    private StartDateValidatorUtil(){}

    public static int startDateValidate(BusTicket busTicket) {
        LocalDate now = LocalDate.now();
        LocalDate date;
        if (busTicket.getStartDate() == null || busTicket.getStartDate().isEmpty()){
            return 1;
        }
        try {
            date = LocalDate.parse(busTicket.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException ex) {
            return 1;
        }
        if (now.isAfter(date) || now.isEqual(date)) {
            return 0;
        } else return 1;
    }

}
