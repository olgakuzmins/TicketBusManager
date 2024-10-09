package com.jfb.lecture5;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfb.lecture5.model.BusTicket;
import com.jfb.lecture5.utils.PriceValidatorUtil;
import com.jfb.lecture5.utils.StartDateValidatorUtil;
import com.jfb.lecture5.utils.TicketTypeValidatorUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
  public static void main(String[] args) throws JsonProcessingException {
    int total = 0;
    int valid = 0;
    int startDate = 0;
    int price = 0;
    int ticketType = 0;
    String mostPopularViolation = "";

    int x = 0;

    do {
      String input = getInput();
      BusTicket busTicket = new ObjectMapper().readValue(input, BusTicket.class);
      total++;
      System.out.println(busTicket.toString());

      int sdv = StartDateValidatorUtil.startDateValidate(busTicket);
      int pv = PriceValidatorUtil.PriceValidate(busTicket);
      int ttv = TicketTypeValidatorUtil.ticketTypeValidate(busTicket);

      int mistakesAmount = sdv + pv + ttv;


      startDate = startDate + sdv;
      price = price + pv;
      ticketType = ticketType + ttv;

      if (mistakesAmount > 0) {
        System.out.println("Warning: there are mistakes in the ticket");
      } else {
        valid++;
      }

      x++;

    } while (x < 5);

    System.out.println("Total = " + total + "\n" +
                       "Valid = " + valid);

    if (valid < total) {
      if (startDate >= price && startDate >= ticketType) {
        mostPopularViolation = "start date";
      }
      else if (price >= ticketType) {
        mostPopularViolation = "price";
      } else {
        mostPopularViolation = "ticket type";
      }
      System.out.println("Most popular violation = " + mostPopularViolation);
    }
  }

  private static String getInput() {
    String name = "src/main/resources/ticketData.txt";
    ArrayList<String> list = new ArrayList<>();

    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(name))))
    {
      while (bufferedReader.ready()) {
        String newTicket = bufferedReader.readLine().replace("“", "\"");
        list.add(newTicket);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    if (!list.isEmpty()) {
      return list.get((int) (Math.random() * list.size()));
    } else {
      throw new IllegalArgumentException("List is empty");
    }
  }

}
