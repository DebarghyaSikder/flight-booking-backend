package com.flightapp.booking_service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.booking_service.entity.Booking;
import com.flightapp.booking_service.service.BookingService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public Booking createBooking(
            @RequestBody Booking booking,
            @RequestHeader("X-User-Email") String email) {

        booking.setUserEmail(email);
        return bookingService.createBooking(booking);
    }
    @GetMapping("/my")
    public List<Booking> getMyBookings(
            @RequestHeader("X-User-Email") String email) {

        return bookingService.getBookingsByUser(email);
    }



}
