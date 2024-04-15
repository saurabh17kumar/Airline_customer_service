package com.TicketBooking.TicketBooking.Repository;

import com.TicketBooking.TicketBooking.Entity.BookedTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookedTicketRepo extends JpaRepository<BookedTicketEntity,Long> {

    @Query("select a from BookedTicketEntity a where a.idCard =:idCard")
    BookedTicketEntity findByAadhar(String idCard);
    @Query("select p from BookedTicketEntity p where p.idCard =:panCard")
    BookedTicketEntity findByPan(String panCard);
}
