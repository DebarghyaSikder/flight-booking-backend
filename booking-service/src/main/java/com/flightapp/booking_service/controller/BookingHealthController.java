package com.flightapp.booking_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingHealthController {

    @GetMapping("/booking/health")
    public String health() {
        return "Booking Service is UP";
    }
}
