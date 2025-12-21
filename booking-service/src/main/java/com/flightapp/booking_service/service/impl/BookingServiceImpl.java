package com.flightapp.booking_service.service.impl;

import org.springframework.stereotype.Service;

import com.flightapp.booking_service.client.FlightClient;
import com.flightapp.booking_service.entity.Booking;
import com.flightapp.booking_service.repository.BookingRepository;
import com.flightapp.booking_service.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final FlightClient flightClient;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              FlightClient flightClient) {
        this.bookingRepository = bookingRepository;
        this.flightClient = flightClient;
    }

    @Override
    public Booking createBooking(Booking booking) {

        Booking savedBooking = bookingRepository.save(booking);

        boolean seatsUpdated = flightClient.updateSeats(
                "true",
                booking.getFlightId(),
                booking.getSeatsBooked()
        );


        if (seatsUpdated) {
            savedBooking.setStatus("CONFIRMED");
        } else {
            savedBooking.setStatus("CANCELLED");
        }

        return bookingRepository.save(savedBooking);
    }
}
