package com.TicketBooking.TicketBooking.Controller;

import com.TicketBooking.TicketBooking.Entity.BookedTicketEntity;
import com.TicketBooking.TicketBooking.Entity.Flight;
import com.TicketBooking.TicketBooking.Entity.SeatManage;
import com.TicketBooking.TicketBooking.Model.*;
import com.TicketBooking.TicketBooking.Service.Service;
import com.TicketBooking.TicketBooking.ServiceImpl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.annotation.PersistenceExceptionTranslationAdvisor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingControl {

    @Autowired
    private Service service;
    Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

    // 2.Customer can book the ticket.
    @PostMapping("/ticket-booked")
    public TicketResponse bookFlightTicket(@RequestBody BookingTicket bookingTicket){
        TicketResponse bookTicket = new TicketResponse();
        if (bookingTicket.getAadhaar() != null || bookingTicket.getPanCard() != null){
            bookTicket = service.bookTicket(bookingTicket);
        }else {
            bookTicket.setMsg("Please fill one of the Id field without Id you not book ticket");
            bookTicket.setCode("1111");
        }
        return bookTicket;
    }

    // 3.Customer can cancel our ticket .
    @DeleteMapping("/delete-ticket")
    public TicketCancellation CancelTicket(@RequestParam(name = "bookingId")Long bookingId){
        TicketCancellation ticketCancellation = new TicketCancellation();
        try {
            if (bookingId != null) {
                ticketCancellation = service.cancelBookTicket(bookingId);
            } else {
                ticketCancellation.setCode("1111");
                ticketCancellation.setMsg("please fill bookingId field");
            }
        }catch (Exception e){
            logger.error("ticket has not cancel",e);
        }
        return ticketCancellation;
    }

    // 10) The admin can view all the flight bookings.
    @GetMapping("/get-all-booked-ticket")
    public List<BookedTicketEntity> bookedTicketData(){
        List<BookedTicketEntity> bookedTicketEntity = new ArrayList<>();
        bookedTicketEntity = service.getAllTicket();
        return bookedTicketEntity;
    }

    @GetMapping("/seat-data")
    public SeatResponse seatData(@RequestParam(name = "flightNo")String flight){
        SeatResponse seatManageList = new SeatResponse();
        seatManageList = service.getAllSeatOfFlight(flight);
        return seatManageList;
    }

    @PostMapping("/post-seat-details")
    public TicketCommonResponse postTicketData(@RequestBody MultipartFile file){
        TicketCommonResponse ticketCommonResponse = new TicketCommonResponse();
        ticketCommonResponse = service.seatExcelupload(file);
        return ticketCommonResponse;
    }

    @GetMapping("/check-flight-ticket")
    public List<FlightAvailability> checkFlightTicket(@RequestParam(name = "flightNo")String flightNo){
        List<FlightAvailability> flightAvailability = new ArrayList<>();
        flightAvailability = service.getAllTicketStatus(flightNo);
        return flightAvailability;
    }
}