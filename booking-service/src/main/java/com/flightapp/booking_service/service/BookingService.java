package com.flightapp.booking_service.service;

import com.flightapp.booking_service.entity.Booking;

import java.util.List;

public interface BookingService {

    Booking createBooking(Booking booking);

    List<Booking> getBookingsByUser(String email);
}
