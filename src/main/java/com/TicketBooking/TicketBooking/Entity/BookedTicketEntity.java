package com.TicketBooking.TicketBooking.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "booked_ticket")
@Data
public class BookedTicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id")
    private Long bookingId;
    @Column(name = "flight_no")//
    private String flightNo;
    @Column(name = "passengerName")//
    private String passengerName;
    @Column(name = "dob")//
    private Date dob;
    @Column(name = "id_card")//
    private String idCard;
    @Column(name = "passengerContactNo")//
    private String passengerContactNo;
    @Column(name = "departure_time")
    private Date departureTime;
    @Column(name = "arrival_time")
    private Date arrivalTime;
    @Column(name = "source_airport")
    private String sourceAirport;
    @Column(name = "destination_airport")
    private String destinationAirport;
    @Column(name = "flight_class")
    private String flightClass;
    @Column(name = "seat_fee")
    private Long seatFee;
    @Column(name = "seat_no")//
    private String seatNo;
    @Column(name = "booking_time")
    private LocalDateTime bookingTime;
    @Column(name = "status")
    private String status;
    @Column(name = "airplane")
    private String airplane;
}
