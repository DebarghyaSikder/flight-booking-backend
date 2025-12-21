package com.flightapp.flight_service.service;

import java.util.List;

import com.flightapp.flight_service.entity.Flight;

public interface FlightService {

    Flight addFlight(Flight flight);

    List<Flight> searchFlights(String source, String destination);

    Flight getFlightById(Long id);
}
