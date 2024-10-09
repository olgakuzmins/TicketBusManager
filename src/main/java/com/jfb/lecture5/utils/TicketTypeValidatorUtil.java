package com.jfb.lecture5.utils;

import com.jfb.lecture5.model.BusTicket;

public final class TicketTypeValidatorUtil {

    private TicketTypeValidatorUtil(){}

    public static int ticketTypeValidate(BusTicket busTicket) {
        if (busTicket.getTicketType() == null || busTicket.getTicketType().isEmpty()){ return 1;}

        return switch (busTicket.getTicketType()) {
            case "DAY", "WEEK", "MONTH", "YEAR" -> 0;
            default -> 1;
        };
    }


}
