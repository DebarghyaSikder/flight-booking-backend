package com.flightapp.flight_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightHealthController {

    @GetMapping("/flight/health")
    public String health() {
        return "Flight Service is UP";
    }
}
