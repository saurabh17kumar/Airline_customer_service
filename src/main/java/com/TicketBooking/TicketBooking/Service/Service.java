package com.TicketBooking.TicketBooking.Service;

import com.TicketBooking.TicketBooking.Entity.BookedTicketEntity;
import com.TicketBooking.TicketBooking.Entity.Flight;
import com.TicketBooking.TicketBooking.Entity.SeatManage;
import com.TicketBooking.TicketBooking.Model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Service {

    TicketResponse bookTicket(BookingTicket bookingTicket);
    TicketCancellation cancelBookTicket(Long bookingId);
    List<BookedTicketEntity> getAllTicket();

    SeatResponse getAllSeatOfFlight(String flight);

    TicketCommonResponse seatExcelupload(MultipartFile file);

    List<FlightAvailability> getAllTicketStatus(String flightNo);
}
