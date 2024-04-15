package com.TicketBooking.TicketBooking.Repository;

import com.TicketBooking.TicketBooking.Entity.SeatManage;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
@Transactional
public interface SeatManageRepo extends JpaRepository<SeatManage,Long> {
    @Query("select s from SeatManage s where s.flightNo =:flight")
    List<SeatManage> findByFlightNo(String flight);

    @Query("SELECT s FROM SeatManage s WHERE s.seatNo = :seat AND s.flightClass = :flightClass AND s.flightNo =:flightNo")
    Optional<SeatManage> findBySeatNo(String seat, String flightClass, String flightNo);
    @Modifying
    @Query("UPDATE SeatManage s SET s.status =:newStatus WHERE s.seatNo =:seatNo AND s.flightClass =:flightClass AND s.status = 'Available'")
    void updateSeatStatus(Long seatNo, String flightClass,String newStatus);

    @Modifying
    @Query("UPDATE SeatManage s SET s.status =:newStatus WHERE s.seatNo =:seatNo AND s.flightClass =:flightClass")
    void updateSeatStatusForDelete(Long seatNo, String flightClass,String newStatus);
}
