package com.flightapp.flight_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flightapp.flight_service.entity.Flight;
import com.flightapp.flight_service.repository.FlightRepository;
import com.flightapp.flight_service.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public Flight addFlight(Flight flight) {
        flight.setAvailableSeats(flight.getTotalSeats());
        return flightRepository.save(flight);
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
