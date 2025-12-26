package com.flightapp.flight_service.controller;

import com.flightapp.flight_service.entity.Seat;
import com.flightapp.flight_service.repository.SeatRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {

    private final SeatRepository seatRepository;

    public SeatController(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    /** ------------------------------------------------
     * GET ALL SEATS FOR A FLIGHT
     * Example: GET /seat/flight/3
     * ------------------------------------------------ */
    @GetMapping("/flight/{flightId}")
    public List<Seat> getSeatsByFlight(@PathVariable Long flightId) {
        return seatRepository.findByFlightId(flightId);
    }

    /** ------------------------------------------------
     * BOOK SELECTED SEATS
     * Example JSON:
     * {
     *   "flightId": 3,
     *   "seatNumbers": ["1A","1B"]
     * }
     * ------------------------------------------------ */
    @PostMapping("/book")
    public String bookSeats(@RequestBody SeatRequest request) {
        for (String seatNo : request.getSeatNumbers()) {
            Seat seat = seatRepository
                    .findByFlightId(request.getFlightId())
                    .stream()
                    .filter(s -> s.getSeatNumber().equals(seatNo))
                    .findFirst()
                    .orElse(null);

            if (seat == null || seat.isBooked()) {
                return "Seat unavailable: " + seatNo;
            }
        }

        // Second pass — update actual booking
        request.getSeatNumbers().forEach(seatNo -> {
            Seat seat = seatRepository.findByFlightId(request.getFlightId())
                    .stream()
                    .filter(s -> s.getSeatNumber().equals(seatNo))
                    .findFirst()
                    .orElseThrow();

            seat.setBooked(true);
            seatRepository.save(seat);
        });

        return "Seats booked successfully: " + request.getSeatNumbers();
    }

    /** ------------------------------------------------
     * UNBOOK / RELEASE SEATS (Optional)
     * ------------------------------------------------ */
    @PostMapping("/unbook")
    public String unbookSeats(@RequestBody SeatRequest request) {
        request.getSeatNumbers().forEach(seatNo -> {
            Seat seat = seatRepository.findByFlightId(request.getFlightId())
                    .stream()
                    .filter(s -> s.getSeatNumber().equals(seatNo))
                    .findFirst()
                    .orElseThrow();

            seat.setBooked(false);
            seatRepository.save(seat);
        });

        return "Seats released: " + request.getSeatNumbers();
    }

    /** DTO for request */
    public static class SeatRequest {
        private Long flightId;
        private List<String> seatNumbers;

        public Long getFlightId() {
            return flightId;
        }
        public void setFlightId(Long flightId) {
            this.flightId = flightId;
        }

        public List<String> getSeatNumbers() {
            return seatNumbers;
        }
        public void setSeatNumbers(List<String> seatNumbers) {
            this.seatNumbers = seatNumbers;
        }
    }
}
