package com.flightapp.flight_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.flightapp.flight_service.entity.Flight;
import com.flightapp.flight_service.service.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // Admin use
    @PostMapping("/add")
    public Flight addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    // User search
    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam String source,
            @RequestParam String destination) {

        return flightService.searchFlights(source, destination);
    }

    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }
    
    @PutMapping("/updateSeats")
    public boolean updateSeats(
            @RequestParam Long flightId,
            @RequestParam int seats) {

        Flight flight = flightService.getFlightById(flightId);

        if (flight == null || flight.getAvailableSeats() < seats) {
            return false;
        }

        flight.setAvailableSeats(flight.getAvailableSeats() - seats);
        flightService.addFlight(flight);

        return true;
    }

}
