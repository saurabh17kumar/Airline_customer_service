package com.TicketBooking.TicketBooking.Model;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
public class TicketResponse extends TicketCommonResponse {
    private Long bookingId;
    private String flightNo;
    private String passengerName;
    private String idCard;
    private String passengerContactNo;
    private Date departureTime;
    private Date arrivalTime;
    private String sourceAirport;
    private String destinationAirport;
    private String flightClass;
    private Long seatFee;
    private String seatNo;
    private LocalDateTime bookingTime;
    private String status;
    private String airplane;

}
