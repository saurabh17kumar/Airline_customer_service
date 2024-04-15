package com.TicketBooking.TicketBooking.Model;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Date;
@Data
public class BookingTicket {

    private String passengerName;
    @NotNull private String passengerContactNo;
    @NotNull private Date dob;
    private String seat;
    private String seatClass;
    @NotNull private String flightNo;
    private String aadhaar;
    private String panCard;

    private TicketCommonResponse ticketCommonResponse;
}
