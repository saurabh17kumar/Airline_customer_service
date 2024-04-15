package com.TicketBooking.TicketBooking.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "air_plane_detail")
public class AirPlaneDetails {
    @Id                 // only admin can add airplane
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "flight_no")
    private String flightNo;
    @Column(name = "airplane_registration_no")
    private String airplaneRegistrationNo;
    @Column(name = "airplane_description")
    private String airplaneDescription;
    @Column(name = "total_seat")
    private Long totalSeat;
    @Column(name = "total_economy_seat")
    private Long totalEconomySeat;
    @Column(name = "total_business_seat")
    private Long totalBusinessSeat;
    @Column(name = "total_first_class_seat")
    private Long totalFirstClassSeat;
    @Column(name = "airplane")
    private String airplane;
}
