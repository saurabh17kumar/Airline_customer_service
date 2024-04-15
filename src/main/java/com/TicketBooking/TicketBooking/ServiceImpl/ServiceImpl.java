package com.TicketBooking.TicketBooking.ServiceImpl;

import com.TicketBooking.TicketBooking.Entity.AirPlaneDetails;
import com.TicketBooking.TicketBooking.Entity.BookedTicketEntity;
import com.TicketBooking.TicketBooking.Entity.Flight;
import com.TicketBooking.TicketBooking.Entity.SeatManage;
import com.TicketBooking.TicketBooking.Model.*;
import com.TicketBooking.TicketBooking.Repository.AirPlaneDetailsRepo;
import com.TicketBooking.TicketBooking.Repository.BookedTicketRepo;
import com.TicketBooking.TicketBooking.Repository.FlightRepo;
import com.TicketBooking.TicketBooking.Repository.SeatManageRepo;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ServiceImpl implements com.TicketBooking.TicketBooking.Service.Service {

    @Autowired
    private BookedTicketRepo bookedTicketRepo;
    @Autowired
    private SeatManageRepo seatManageRepo;
    @Autowired
    private FlightRepo flightRepo;
    @Autowired
    private AirPlaneDetailsRepo airPlaneDetailsRepo;
    @Autowired
    private SeatManageUpdateDelete seatManageUpdateDelete;
    Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

//    public TicketResponse bookTicket(BookingTicket bookingTicket) {
//
//        BookedTicketEntity bookTicket = new BookedTicketEntity();
//        BookedTicketEntity check = new BookedTicketEntity();
//        TicketResponse bookTicketResponse = new TicketResponse();
//        Optional<SeatManage> seatManage = Optional.of(new SeatManage());
//        SeatManage seatManages = new SeatManage();
//        Flight flight = new Flight();
//        AirPlaneDetails airPlaneDetails = new AirPlaneDetails();
//        String errorMsg = "";
//
//        try {
//
//            if (bookingTicket.getAadhaar() != null) {
//                check = bookedTicketRepo.findByAadhar(bookingTicket.getAadhaar());
//            } else {
//                check = bookedTicketRepo.findByPan(bookingTicket.getPanCard());
//            }
//            if (check == null) {
//
//                if (bookingTicket.getFlightNo() != null) {
//                    seatManage = seatManageRepo.findBySeatNo(bookingTicket.getSeat(),bookingTicket.getSeatClass(),bookingTicket.getFlightNo());
//                    flight = flightRepo.findByFlightNo(bookingTicket.getFlightNo());
//                    airPlaneDetails = airPlaneDetailsRepo.findByFlightNo(bookingTicket.getFlightNo());
//                    if (seatManage.isPresent()) {
//                        seatManages = seatManage.get();
//                        if (seatManages.getStatus().equals("Available")) {
//                            if (flight != null) {
//                                if (bookingTicket.getSeatClass().contains("B")) {
//                                    bookTicket.setSeatFee(flight.getBusinessSeatFare());
//                                } else if (bookingTicket.getSeatClass().contains("C")) {
//                                    bookTicket.setSeatFee(flight.getEconomySeatFare());
//                                } else if (bookingTicket.getSeatClass().contains("A")) {
//                                    bookTicket.setSeatFee(flight.getFirstClassSeatFare());
//                                }
//                                bookTicket.setFlightNo(bookingTicket.getFlightNo());
//                                bookTicket.setPassengerContactNo(bookingTicket.getPassengerContactNo());
//                                bookTicket.setFlightClass(bookingTicket.getSeatClass());
//                                bookTicket.setPassengerName(bookingTicket.getPassengerName());
//                                bookTicket.setSeatNo(bookingTicket.getSeat());
//                                bookTicket.setDob(bookingTicket.getDob());
//                                if (bookingTicket.getAadhaar() != null) {
//                                    bookTicket.setIdCard(bookingTicket.getAadhaar());
//                                } else {
//                                    bookTicket.setIdCard(bookingTicket.getPanCard());
//                                }
//                                bookTicket.setBookingTime(LocalDateTime.now());
//                                bookTicket.setAirplane(flight.getAirplane());
//                                bookTicket.setArrivalTime(flight.getSelectArrivalTime());
//                                bookTicket.setDepartureTime(flight.getSelectDepartureTime());
//                                bookTicket.setSourceAirport(flight.getDepartureAirport());
//                                bookTicket.setDestinationAirport(flight.getArrivalAirport());
//                                bookTicket.setStatus("Confirm");
//
//                                bookTicketResponse.setFlightNo(bookingTicket.getFlightNo());
//                                bookTicketResponse.setAirplane(bookTicket.getAirplane());
//                                bookTicketResponse.setBookingTime(bookTicket.getBookingTime());
//                                bookTicketResponse.setArrivalTime(bookTicket.getArrivalTime());
//                                bookTicketResponse.setDepartureTime(bookTicket.getDepartureTime());
//                                bookTicketResponse.setStatus(bookTicket.getStatus());
//                                bookTicketResponse.setFlightClass(bookTicket.getFlightClass());
//                                bookTicketResponse.setDestinationAirport(bookTicket.getDestinationAirport());
//                                bookTicketResponse.setSourceAirport(bookTicket.getSourceAirport());
//                                bookTicketResponse.setAirplane(bookTicket.getAirplane());
//                                bookTicketResponse.setSeatNo(bookTicket.getSeatNo());
//                                bookTicketResponse.setSeatFee(bookTicket.getSeatFee());
//                                bookTicketResponse.setIdCard(bookTicket.getIdCard());
//                                bookTicketResponse.setPassengerContactNo(bookTicket.getPassengerContactNo());
//                                bookTicketResponse.setPassengerName(bookTicket.getPassengerName());
//
//                            } else {
//                                bookTicketResponse.setMsg("Flight not available please fill correct flight no:");
//                                bookTicketResponse.setCode("1111");
//                                errorMsg = "flight not available";
//                            }
//
//                            if (errorMsg.isEmpty()) {
//                                String newStatus = "Confirm";
//                                seatManageRepo.updateSeatStatus(seatManages.getSeatNo(), seatManages.getFlightClass(), newStatus);
//                                try {
//                                    bookedTicketRepo.save(bookTicket);
//                                    bookTicketResponse.setBookingId(bookTicket.getBookingId());
//                                    bookTicketResponse.setMsg("Your ticket Book successfully");
//                                    bookTicketResponse.setCode("0000");
//                                } catch (Exception e) {
//                                    bookTicketResponse.setCode("1111");
//                                    bookTicketResponse.setMsg("your ticket does not booked");
//                                }
//                            }else {
//                                bookTicketResponse.setMsg("Your ticket not Book ");
//                                bookTicketResponse.setCode("1111");
//                            }
//                            if (flight != null) {
//                                Long newSeat = airPlaneDetails.getTotalSeat() - 1;
//                                flightRepo.seatAvailabilityUpdateByFlightNo(bookingTicket.getFlightNo(), newSeat);
//                            }
//
//                            if (airPlaneDetails != null) {
//                                Long newSeat = airPlaneDetails.getTotalSeat() - 1;
//                                airPlaneDetailsRepo.seatAvailabilityUpdateByFlightNo(bookingTicket.getFlightNo(), newSeat);
//                                if (bookingTicket.getSeatClass().contains("B")) {
//                                     Long newSeatB = airPlaneDetails.getTotalBusinessSeat() - 1;
//                                    airPlaneDetailsRepo.seatUpdateByClassB(bookingTicket.getFlightNo(), newSeatB);
//                                }else if (bookingTicket.getSeatClass().contains("A")){
//                                    Long newSeatA = airPlaneDetails.getTotalFirstClassSeat() - 1;
//                                    airPlaneDetailsRepo.seatUpdateByClassA(bookingTicket.getFlightNo(), newSeatA);
//                                } else if (bookingTicket.getSeatClass().contains("C")) {
//                                    Long newSeatC = airPlaneDetails.getTotalEconomySeat() - 1;
//                                    airPlaneDetailsRepo.seatUpdateByClassC(bookingTicket.getFlightNo(), newSeatC);
//                                }
//                            }
//                        } else {
//                            bookTicketResponse.setMsg("This seat was not available :" + bookingTicket.getSeat());
//                            bookTicketResponse.setCode("1111");
//                        }
//                    }else {
//                        bookTicketResponse.setMsg("This seat is no valid");
//                        bookTicketResponse.setCode("1111");
//                    }
//                }
//            } else {
//                bookTicketResponse.setCode("1111");
//                bookTicketResponse.setMsg("your id was already in use to book ticket. ");
//            }
//        } catch (Exception e) {
//            logger.error("Ticket not book :", e);
//        }
//        return bookTicketResponse;
//    }

    public TicketResponse bookTicket(BookingTicket bookingTicket) {
        TicketResponse bookTicketResponse = new TicketResponse();
        String errorMsg = "";

        try {
            BookedTicketEntity check = bookingTicket.getAadhaar() != null ?
                    bookedTicketRepo.findByAadhar(bookingTicket.getAadhaar()) :
                    bookedTicketRepo.findByPan(bookingTicket.getPanCard());

            if (check != null) {
                bookTicketResponse.setCode("1111");
                bookTicketResponse.setMsg("Your ID was already used to book a ticket.");
                return bookTicketResponse;
            }

            Optional<SeatManage> seatManage = seatManageRepo.findBySeatNo(bookingTicket.getSeat(),
                    bookingTicket.getSeatClass(), bookingTicket.getFlightNo());
            if (seatManage.isEmpty()) {
                bookTicketResponse.setCode("1111");
                bookTicketResponse.setMsg("This seat is not valid.");
                return bookTicketResponse;
            }

            SeatManage seatManages = seatManage.get();
            if (!seatManages.getStatus().equals("Available")) {
                bookTicketResponse.setCode("1111");
                bookTicketResponse.setMsg("This seat is not available: " + bookingTicket.getSeat());
                return bookTicketResponse;
            }

            Flight flight = flightRepo.findByFlightNo(bookingTicket.getFlightNo());
            if (flight == null) {
                bookTicketResponse.setCode("1111");
                bookTicketResponse.setMsg("Flight not available. Please enter a correct flight number.");
                return bookTicketResponse;
            }

            AirPlaneDetails airPlaneDetails = airPlaneDetailsRepo.findByFlightNo(bookingTicket.getFlightNo());
            if (airPlaneDetails == null) {
                bookTicketResponse.setCode("1111");
                bookTicketResponse.setMsg("Flight details not available.");
                return bookTicketResponse;
            }

            long newSeat = airPlaneDetails.getTotalSeat() - 1;
            airPlaneDetails.setTotalSeat(newSeat);
//            airPlaneDetailsRepo.save(airPlaneDetails);
            String seatClass = bookingTicket.getSeatClass().toUpperCase();
            if (seatClass.contains("B")) {
                airPlaneDetails.setTotalBusinessSeat(airPlaneDetails.getTotalBusinessSeat() - 1);
            } else if (seatClass.contains("A")) {
                airPlaneDetails.setTotalFirstClassSeat(airPlaneDetails.getTotalFirstClassSeat() - 1);
            } else if (seatClass.contains("C")) {
                airPlaneDetails.setTotalEconomySeat(airPlaneDetails.getTotalEconomySeat() - 1);
            }
            airPlaneDetailsRepo.save(airPlaneDetails);

            String newStatus = "Confirm";
            seatManageRepo.updateSeatStatus(seatManages.getSeatNo(), seatManages.getFlightClass(), newStatus);

            BookedTicketEntity bookTicket = createBookedTicket(bookingTicket, flight);
            bookedTicketRepo.save(bookTicket);

            setTicketResponse(bookTicketResponse, bookTicket);

        } catch (Exception e) {
            bookTicketResponse.setCode("1111");
            bookTicketResponse.setMsg("Ticket booking failed.");
            logger.error("Ticket not booked: ", e);
        }
        return bookTicketResponse;
    }

    private BookedTicketEntity createBookedTicket(BookingTicket bookingTicket, Flight flight) {
        BookedTicketEntity bookTicket = new BookedTicketEntity();
        bookTicket.setSeatFee(getSeatFee(bookingTicket, flight));
        bookTicket.setFlightNo(bookingTicket.getFlightNo());
        bookTicket.setPassengerContactNo(bookingTicket.getPassengerContactNo());
        bookTicket.setFlightClass(bookingTicket.getSeatClass());
        bookTicket.setPassengerName(bookingTicket.getPassengerName());
        bookTicket.setSeatNo(bookingTicket.getSeat());
        bookTicket.setDob(bookingTicket.getDob());
        bookTicket.setIdCard(bookingTicket.getAadhaar() != null ? bookingTicket.getAadhaar() : bookingTicket.getPanCard());
        bookTicket.setBookingTime(LocalDateTime.now());
        bookTicket.setAirplane(flight.getAirplane());
        bookTicket.setArrivalTime(flight.getSelectArrivalTime());
        bookTicket.setDepartureTime(flight.getSelectDepartureTime());
        bookTicket.setSourceAirport(flight.getDepartureAirport());
        bookTicket.setDestinationAirport(flight.getArrivalAirport());
        bookTicket.setStatus("Confirm");
        return bookTicket;
    }

    private void setTicketResponse(TicketResponse bookTicketResponse, BookedTicketEntity bookTicket) {
        bookTicketResponse.setBookingId(bookTicket.getBookingId());
        bookTicketResponse.setMsg("Your ticket has been booked successfully.");
        bookTicketResponse.setCode("0000");
        bookTicketResponse.setFlightNo(bookTicket.getFlightNo());
        bookTicketResponse.setAirplane(bookTicket.getAirplane());
        bookTicketResponse.setBookingTime(bookTicket.getBookingTime());
        bookTicketResponse.setArrivalTime(bookTicket.getArrivalTime());
        bookTicketResponse.setDepartureTime(bookTicket.getDepartureTime());
        bookTicketResponse.setStatus(bookTicket.getStatus());
        bookTicketResponse.setFlightClass(bookTicket.getFlightClass());
        bookTicketResponse.setDestinationAirport(bookTicket.getDestinationAirport());
        bookTicketResponse.setSourceAirport(bookTicket.getSourceAirport());
        bookTicketResponse.setSeatNo(bookTicket.getSeatNo());
        bookTicketResponse.setSeatFee(bookTicket.getSeatFee());
        bookTicketResponse.setIdCard(bookTicket.getIdCard());
        bookTicketResponse.setPassengerContactNo(bookTicket.getPassengerContactNo());
        bookTicketResponse.setPassengerName(bookTicket.getPassengerName());
    }

    private Long getSeatFee(BookingTicket bookingTicket, Flight flight) {
        String seatClass = bookingTicket.getSeatClass().toUpperCase();
        if (seatClass.contains("B")) {
            return flight.getBusinessSeatFare();
        } else if (seatClass.contains("C")) {
            return flight.getEconomySeatFare();
        } else if (seatClass.contains("A")) {
            return flight.getFirstClassSeatFare();
        }
        return 0L;
    }

    public TicketCancellation cancelBookTicket(Long bookingId) {
        TicketCancellation ticketCancellation = new TicketCancellation();
        String errorMsg = "";

        if (bookingId == null) {
            errorMsg = "Please provide a valid booking ID.";
        } else {
            Optional<BookedTicketEntity> bookedTicketEntityOpt = bookedTicketRepo.findById(bookingId);
            if (bookedTicketEntityOpt.isPresent()) {
                BookedTicketEntity passenger = bookedTicketEntityOpt.get();
                Optional<SeatManage> seatManage = seatManageRepo.findBySeatNo(passenger.getSeatNo(), passenger.getFlightClass(), passenger.getFlightNo());
                Flight flight = flightRepo.findByFlightNo(passenger.getFlightNo());
                AirPlaneDetails airPlaneDetails = airPlaneDetailsRepo.findByFlightNo(passenger.getFlightNo());

                bookedTicketRepo.deleteById(bookingId);

                if (bookedTicketRepo.findById(bookingId).isEmpty()) {
                    ticketCancellation.setCode("0000");
                    ticketCancellation.setBookingId(bookingId);
                    ticketCancellation.setStatus("deleted");
                    ticketCancellation.setPassengerName(passenger.getPassengerName());
                    ticketCancellation.setMsg("Your ticket has been canceled.");
//                    updateSeatAvailability(flight, passenger.getFlightClass(), true);
                } else {
                    errorMsg = "Ticket was not canceled.";
                }

                if (errorMsg.isEmpty()) {
                    seatManageUpdateDelete.updateSeatAvailability(flight, airPlaneDetails, passenger.getFlightClass(), true,true,seatManage );
                }
            } else {
                errorMsg = "Invalid booking ID. Please insert a valid ID.";
            }
        }

        if (!errorMsg.isEmpty()) {
            ticketCancellation.setCode("1111");
            ticketCancellation.setMsg(errorMsg);
        }

        return ticketCancellation;
    }


//    public TicketCancellation cancelBookTicket(Long bookingId) {
//
//        TicketCancellation ticketCancellation = new TicketCancellation();
//        AirPlaneDetails airPlaneDetails = new AirPlaneDetails();
//        Flight flight = new Flight();
//        String errorMsg = "";
//        BookedTicketEntity passenger = null;
//
//
//        if (bookingId != null) {
//            Optional<BookedTicketEntity> bookedTicketEntity = bookedTicketRepo.findById(bookingId);
//            if (bookedTicketEntity.isPresent()) {
//                passenger = bookedTicketEntity.get();
//                flight = flightRepo.findByFlightNo(passenger.getFlightNo());
//                airPlaneDetails = airPlaneDetailsRepo.findByFlightNo(passenger.getFlightNo());
//                bookedTicketRepo.deleteById(bookingId);
//            } else {
//                ticketCancellation.setBookingId(bookingId);
//                ticketCancellation.setCode("1111");
//                ticketCancellation.setMsg("this booking Id was not valid please insert a valid Id.");
//                errorMsg = "this booking Id was not valid please insert a valid Id.";
//            }
//            Optional<BookedTicketEntity> checkTicketDeleteOrNot = bookedTicketRepo.findById(bookingId);
//            if (checkTicketDeleteOrNot.isEmpty()) {
//                ticketCancellation.setCode("0000");
//                ticketCancellation.setBookingId(bookingId);
//                ticketCancellation.setStatus("deleted");
//                assert passenger != null;
//                ticketCancellation.setPassengerName(passenger.getPassengerName());
//                ticketCancellation.setMsg("your ticket has been canceled");
//            } else {
//                errorMsg = "Ticket was not cancel";
//            }
//            if (errorMsg.isEmpty()) {
//                flightRepo.seatAvailabilityUpdateByFlightNo(passenger.getFlightNo(), flight.getSeatAvailable() + 1);
//                airPlaneDetailsRepo.seatAvailabilityUpdateByFlightNo(passenger.getFlightNo(), airPlaneDetails.getTotalSeat() + 1);
//                if (passenger.getFlightClass().contains("B")) {
//                    Long newSeatB = airPlaneDetails.getTotalBusinessSeat() + 1;
//                    airPlaneDetailsRepo.seatUpdateByClassB(passenger.getFlightNo(), newSeatB);
//                }else if (passenger.getFlightClass().contains("A")){
//                    Long newSeatA = airPlaneDetails.getTotalFirstClassSeat() + 1;
//                    airPlaneDetailsRepo.seatUpdateByClassA(passenger.getFlightNo(), newSeatA);
//                } else if (passenger.getFlightClass().contains("C")) {
//                    Long newSeatC = airPlaneDetails.getTotalEconomySeat() + 1;
//                    airPlaneDetailsRepo.seatUpdateByClassC(passenger.getFlightNo(), newSeatC);
//                }
//            }
//        }
//        return ticketCancellation;
//    }

    public List<BookedTicketEntity> getAllTicket() {
        List<BookedTicketEntity> bookedTicketEntity = new ArrayList<>();
        bookedTicketEntity = bookedTicketRepo.findAll();
        return bookedTicketEntity;
    }

    public SeatResponse getAllSeatOfFlight(String flight) {
        List<SeatManage> seatManageList = new ArrayList<>();
        SeatResponse seatResponse = new SeatResponse();
        try {
            seatManageList = seatManageRepo.findByFlightNo(flight);

            if (seatManageList.isEmpty()) {
                seatResponse.setCode("1111");
                seatResponse.setMsg("Data not found");
            } else {
                seatResponse.setMsg("Data found successfully");
                seatResponse.setCode("0000");
                seatResponse.setSeatManageList(seatManageList);
            }
        } catch (Exception e) {
            logger.error("Data not found exception :", e);
        }
        return seatResponse;
    }

    public TicketCommonResponse seatExcelupload(MultipartFile file) {
        TicketCommonResponse ticketCommonResponse = new TicketCommonResponse();
        List<SeatManage> seatManageList = new ArrayList<>();
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            int count = 0;
            Row headerRow = rowIterator.next();
            while (rowIterator.hasNext()) {
                count++;
                Row row = rowIterator.next();
                SeatManage seatManage = new SeatManage();
                for (int i = 0; i < 4; i++) {
                    Cell cell = row.getCell(i);
                    switch (i) {
                        case 0:
                            seatManage.setSeatNo(Long.valueOf(row.getCell(0).toString().replace(".0","")));
                            break;
                        case 1:
                            seatManage.setStatus(row.getCell(1).toString());
                            break;
                        case 2:
                            seatManage.setFlightNo(row.getCell(2).toString());
                            break;
                        case 3:
                            seatManage.setFlightClass(row.getCell(3).toString());
                            break;
                    }

                }
                seatManageList.add(seatManage);
            }
            seatManageRepo.saveAll(seatManageList);
            System.out.println(seatManageList);
        }catch (Exception e){
            logger.error("error",e);
        }
        if (seatManageList.isEmpty()){
            ticketCommonResponse.setCode("1111");
            ticketCommonResponse.setMsg("Data not save");
        }else {
            ticketCommonResponse.setMsg("Data save ");
            ticketCommonResponse.setCode("0000");
        }
        return ticketCommonResponse;
    }

    public List<FlightAvailability> getAllTicketStatus(String flightNo){
        List<FlightAvailability> flightAvailability = new ArrayList<>();
        Flight flight = new Flight();
        flightAvailability = flightRepo.findByFlightNoForSeat(flightNo);
        return flightAvailability;
    }
}