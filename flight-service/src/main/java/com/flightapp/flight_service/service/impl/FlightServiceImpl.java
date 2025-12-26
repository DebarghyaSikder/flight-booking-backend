package com.flightapp.flight_service.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.flightapp.flight_service.entity.Flight;
import com.flightapp.flight_service.entity.Seat;
import com.flightapp.flight_service.repository.FlightRepository;
import com.flightapp.flight_service.repository.SeatRepository;
import com.flightapp.flight_service.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;

    public FlightServiceImpl(FlightRepository flightRepository,
                             SeatRepository seatRepository) {
        this.flightRepository = flightRepository;
        this.seatRepository = seatRepository;
    }

    @Override
    public Flight addFlight(Flight flight) {

        // Set available seats = total seats initially
        flight.setAvailableSeats(flight.getTotalSeats());
        Flight savedFlight = flightRepository.save(flight);

        // -------- SEAT GENERATION LOGIC ---------
        int total = savedFlight.getTotalSeats();
        int seatsPerRow = 6;           // e.g. A,B,C,D,E,F  
        char[] seatLetters = {'A','B','C','D','E','F'};

        List<Seat> seats = new ArrayList<>();

        for (int i = 1; i <= total; i++) {
            int row = (i - 1) / seatsPerRow + 1;
            char letter = seatLetters[(i - 1) % seatsPerRow];

            String seatNumber = row + "" + letter; // e.g. 1A, 1B..
            seats.add(new Seat(savedFlight.getId(), seatNumber));
        }

        seatRepository.saveAll(seats);
        System.out.println("✔ Seats generated: " + seats.size());

        return savedFlight;
    }

    @Override
    public List<Flight> searchFlights(String source, String destination) {
        return flightRepository.findBySourceAndDestination(source, destination);
    }

    
    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }
}
