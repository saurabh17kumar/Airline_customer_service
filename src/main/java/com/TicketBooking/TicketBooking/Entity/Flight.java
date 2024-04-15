package com.TicketBooking.TicketBooking.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "flight_no")
    private String flightNo;
    @Column(name = "airplane")
    private String airplane;//2
    @Column(name = "departure_airport")
    private String departureAirport;//6
    @Column(name = "arrival_airport")
    private String arrivalAirport;//7
    @Column(name = "flight_status")
    private String flightStatus;//11
    @Column(name = "select_departure_time")
    private Date selectDepartureTime;//4
    @Column(name = "select_arrival_time")
    private Date selectArrivalTime;//5
    @Column(name = "economy_seat_fare")
    private Long economySeatFare;//8
    @Column(name = "business_seat_fare")
    private Long businessSeatFare;//9
    @Column(name = "first_class_seat_fare")
    private Long firstClassSeatFare;//10
    @Column(name = "seat_available")
    private Long seatAvailable;//12

//    @OneToMany(mappedBy = "flights")
//    private List<SeatManage> seatManageList;
}
