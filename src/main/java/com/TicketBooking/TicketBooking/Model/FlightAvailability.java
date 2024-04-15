package com.TicketBooking.TicketBooking.Model;

import lombok.Data;

import java.sql.Date;

@Data
public class FlightAvailability {
    private String flightNo;
    private String airplane;
    private String departureAirport;
    private String arrivalAirport;
    private String flightStatus;
    private Date selectDepartureTime;
    private Date selectArrivalTime;
    private Long economySeatFare;
    private Long businessSeatFare;
    private Long firstClassSeatFare;
    private Long seatAvailable;
    private String flightClass;
    private Long seatNo;
    private String status;

    public FlightAvailability(String flightNo, String airplane, String departureAirport, String arrivalAirport, String flightStatus, Date selectDepartureTime, Date selectArrivalTime, Long economySeatFare, Long businessSeatFare, Long firstClassSeatFare, Long seatAvailable, String flightClass, Long seatNo, String status) {
        this.flightNo = flightNo;
        this.airplane = airplane;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.flightStatus = flightStatus;
        this.selectDepartureTime = selectDepartureTime;
        this.selectArrivalTime = selectArrivalTime;
        this.economySeatFare = economySeatFare;
        this.businessSeatFare = businessSeatFare;
        this.firstClassSeatFare = firstClassSeatFare;
        this.seatAvailable = seatAvailable;
        this.flightClass = flightClass;
        this.seatNo = seatNo;
        this.status = status;
    }

    // Getters and setters
}
