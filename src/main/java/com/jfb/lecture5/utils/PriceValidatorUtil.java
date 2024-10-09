package com.jfb.lecture5.utils;

import com.jfb.lecture5.model.BusTicket;

public final class PriceValidatorUtil {
    private PriceValidatorUtil(){}

    public static int PriceValidate(BusTicket busTicket) {
        int integerPrice = 0;

        if (busTicket.getPrice() == null || busTicket.getPrice().equals("0")) {
            return 1;
        }

        try {
            integerPrice = Integer.parseInt(busTicket.getPrice());
        } catch (NumberFormatException e) {
            return 1;
        }

        if (integerPrice %2 == 0) {
            return 0;
        } else return 1;
    }
}
