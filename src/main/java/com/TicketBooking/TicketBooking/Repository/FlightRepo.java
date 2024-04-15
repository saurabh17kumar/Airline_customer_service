package com.TicketBooking.TicketBooking.Repository;

import com.TicketBooking.TicketBooking.Entity.Flight;
import com.TicketBooking.TicketBooking.Model.FlightAvailability;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface FlightRepo extends JpaRepository<Flight,Long> {
    @Query("SELECT pp FROM Flight pp " +
            "WHERE (:startDate IS NULL OR pp.selectDepartureTime >= :startDate) " +
            "AND (:endDate IS NULL OR pp.selectDepartureTime <= :endDate) " +
            "AND (:toAirport IS NULL OR pp.departureAirport = :toAirport) " +
            "AND (:fromAirport IS NULL OR pp.arrivalAirport = :fromAirport)")
    List<Flight> findByToAirportAndFromAirportAndStartDateAndEndDate(Date startDate, Date endDate, String toAirport, String fromAirport);
    @Modifying
    @Query("UPDATE Flight SET selectArrivalTime =:selectArrivalTime, selectDepartureTime =:selectDepartureTime,departureAirport =:departureAirport, arrivalAirport =:arrivalAirport WHERE flightNo = :flightNo")
    void updateByFlightNo(Date selectArrivalTime, Date selectDepartureTime, String departureAirport, String arrivalAirport, String flightNo);
    @Query("select f from Flight f where f.flightNo =:flightNo")
    Flight findByFlightNo(String flightNo);
    //    @Query("select f from Flight f where f.flightNo =:flightNo")
//    List<Flight> findByFlightNoList(String flightNo);
    @Modifying
    @Transactional
    @Query("UPDATE Flight SET seatAvailable =:flight1 WHERE flightNo = :flightNo")
    void seatAvailabilityUpdateByFlightNo(String flightNo, Long flight1);
    @Query("SELECT NEW com.TicketBooking.TicketBooking.Model.FlightAvailability(f.flightNo, f.airplane, f.departureAirport, f.arrivalAirport, s.status, f.selectDepartureTime, f.selectArrivalTime, f.economySeatFare, f.businessSeatFare, f.firstClassSeatFare, f.seatAvailable, s.flightClass, s.seatNo, s.status) FROM SeatManage s JOIN Flight f ON s.flightNo = f.flightNo WHERE f.flightNo = :flightNo")
    List<FlightAvailability> findByFlightNoForSeat(String flightNo);
}
