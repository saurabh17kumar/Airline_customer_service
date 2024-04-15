package com.TicketBooking.TicketBooking.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "seat_management")
@Data
public class SeatManage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "flight_no")
    private String flightNo;
    @Column(name = "status")
    private String status;
    @Column(name = "seat_no")
    private Long seatNo;
    @Column(name = "flight_class")
    private String flightClass;

//    @JsonIgnore
//    @ManyToOne
//    @JoinColumn(name = "flight_no")
//    private Flight flights;
}