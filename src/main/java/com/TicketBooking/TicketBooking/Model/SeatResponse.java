package com.TicketBooking.TicketBooking.Model;

import com.TicketBooking.TicketBooking.Entity.SeatManage;
import lombok.Data;

import java.util.List;

@Data
public class SeatResponse {
    private String msg;
    private String code;

    private List<SeatManage> seatManageList;
}
