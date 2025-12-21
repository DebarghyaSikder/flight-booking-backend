package com.flightapp.booking_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "flight-service")
public interface FlightClient {

    @PutMapping("/flight/updateSeats")
    boolean updateSeats(
        @RequestHeader("X-Internal-Call") String internal,
        @RequestParam Long flightId,
        @RequestParam int seats
    );
}

