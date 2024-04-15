package com.TicketBooking.TicketBooking.Model;

import lombok.Data;

@Data
public class TicketCancellation {
    private Long bookingId;
    private String msg;
    private String code;
    private String passengerName;
    private  String status;
}
