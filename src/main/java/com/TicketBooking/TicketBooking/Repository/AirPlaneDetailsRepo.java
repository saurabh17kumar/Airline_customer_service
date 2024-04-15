package com.TicketBooking.TicketBooking.Repository;

import com.TicketBooking.TicketBooking.Entity.AirPlaneDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface AirPlaneDetailsRepo extends JpaRepository<AirPlaneDetails,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE AirPlaneDetails SET totalSeat =:newSeat WHERE flightNo = :flightNo")
    void seatAvailabilityUpdateByFlightNo(String flightNo, Long newSeat);
    @Query("select f from AirPlaneDetails f where f.flightNo =:flightNo")
    AirPlaneDetails findByFlightNo(String flightNo);
    @Modifying
    @Query("UPDATE AirPlaneDetails SET totalBusinessSeat =:newSeatB WHERE flightNo = :flightNo")
    void seatUpdateByClassB(String flightNo, Long newSeatB);
    @Modifying
    @Query("UPDATE AirPlaneDetails SET totalFirstClassSeat =:newSeatA WHERE flightNo = :flightNo")
    void seatUpdateByClassA(String flightNo, Long newSeatA);
    @Modifying
    @Query("UPDATE AirPlaneDetails SET totalEconomySeat =:newSeatC WHERE flightNo = :flightNo")
    void seatUpdateByClassC(String flightNo, Long newSeatC);
}
