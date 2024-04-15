package com.TicketBooking.TicketBooking.ServiceImpl;

import com.TicketBooking.TicketBooking.Entity.AirPlaneDetails;
import com.TicketBooking.TicketBooking.Entity.Flight;
import com.TicketBooking.TicketBooking.Entity.SeatManage;
import com.TicketBooking.TicketBooking.Repository.AirPlaneDetailsRepo;
import com.TicketBooking.TicketBooking.Repository.FlightRepo;
import com.TicketBooking.TicketBooking.Repository.SeatManageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatManageUpdateDelete {

    @Autowired
    private SeatManageRepo seatManageRepo;
    @Autowired
    private FlightRepo flightRepo;
    @Autowired
    private AirPlaneDetailsRepo airPlaneDetailsRepo;

    public void updateSeatAvailability(Flight flight, AirPlaneDetails airPlaneDetails, String flightClass, boolean increase, boolean available, Optional<SeatManage> seatManage) {
        if (available && seatManage.isPresent()) {
            SeatManage seat = seatManage.get();
            // Update status to "Available"
            seatManageRepo.updateSeatStatusForDelete(seat.getSeatNo(), flightClass, "Available");
        }
        long increment = increase ? 1 : -1;
        flightRepo.seatAvailabilityUpdateByFlightNo(flight.getFlightNo(), flight.getSeatAvailable() + increment);
        airPlaneDetailsRepo.seatAvailabilityUpdateByFlightNo(airPlaneDetails.getFlightNo(), airPlaneDetails.getTotalSeat() + increment);

        if (flightClass.contains("B")) {
            airPlaneDetailsRepo.seatUpdateByClassB(airPlaneDetails.getFlightNo(), airPlaneDetails.getTotalBusinessSeat() + increment);
        } else if (flightClass.contains("A")) {
            airPlaneDetailsRepo.seatUpdateByClassA(airPlaneDetails.getFlightNo(), airPlaneDetails.getTotalFirstClassSeat() + increment);
        } else if (flightClass.contains("C")) {
            airPlaneDetailsRepo.seatUpdateByClassC(airPlaneDetails.getFlightNo(), airPlaneDetails.getTotalEconomySeat() + increment);
        }
    }
}
