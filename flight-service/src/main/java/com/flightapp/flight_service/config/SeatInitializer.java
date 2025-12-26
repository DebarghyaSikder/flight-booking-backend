package com.flightapp.flight_service.config;

import com.flightapp.flight_service.entity.Flight;
import com.flightapp.flight_service.entity.Seat;
import com.flightapp.flight_service.repository.FlightRepository;
import com.flightapp.flight_service.repository.SeatRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class SeatInitializer {

    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;

    public SeatInitializer(FlightRepository flightRepository,
                           SeatRepository seatRepository) {
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
    }

    @PostConstruct
    public void generateSeatsForExistingFlights() {
        var flights = flightRepository.findAll();

        for (Flight flight : flights) {
            int count = seatRepository.findByFlightId(flight.getId()).size();
            if (count > 0) {
                continue; // seats already generated
            }

            generateSeats(flight);
        }
    }

    private void generateSeats(Flight flight) {
        int rows = flight.getTotalSeats() / 6;
        int seatNumber = 1;

        for (int row = 1; row <= rows; row++) {
            for (char col = 'A'; col <= 'F'; col++) {

                if (seatNumber > flight.getTotalSeats()) {
                    return;
                }

                Seat seat = new Seat();
                seat.setFlightId(flight.getId());
                seat.setSeatNumber(row + String.valueOf(col));
                seat.setBooked(false);   // <-- changed here
                seatRepository.save(seat);

                seatNumber++;
            }
        }
    }

}
