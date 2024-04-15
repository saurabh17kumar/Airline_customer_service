package com.TicketBooking.TicketBooking.Model;

import lombok.Data;

@Data
public class SeatDataModel {

    private Long id;
    private String flightNo;
    private String status;
    private Long seatNo;
    private String column;

}
