package com.flightapp.booking_service.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.flightapp.booking_service.client.FlightClient;
import com.flightapp.booking_service.entity.Booking;
import com.flightapp.booking_service.event.BookingConfirmedEvent;
import com.flightapp.booking_service.repository.BookingRepository;
import com.flightapp.booking_service.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final FlightClient flightClient;
    private final RabbitTemplate rabbitTemplate;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              FlightClient flightClient,
                              RabbitTemplate rabbitTemplate) {
        this.bookingRepository = bookingRepository;
        this.flightClient = flightClient;
        this.rabbitTemplate = rabbitTemplate;
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
            bookingRepository.save(savedBooking);

            BookingConfirmedEvent event =
                    new BookingConfirmedEvent(
                            savedBooking.getId(),
                            savedBooking.getUserEmail(),
                            savedBooking.getFlightId(),
                            savedBooking.getSeatsBooked()
                    );

            rabbitTemplate.convertAndSend(
                    "booking.exchange",
                    "booking.confirmed",
                    event
            );

        } else {
            savedBooking.setStatus("CANCELLED");
            bookingRepository.save(savedBooking);
        }

        return savedBooking;
    }
}
