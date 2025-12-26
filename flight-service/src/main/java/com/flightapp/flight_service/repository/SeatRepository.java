package com.flightapp.flight_service.repository;

import com.flightapp.flight_service.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    // Get all seats for a flight
    List<Seat> findByFlightId(Long flightId);

    // Get booked OR available seats
    List<Seat> findByFlightIdAndBooked(Long flightId, boolean booked);

    // Check if a particular seat exists for a flight
    boolean existsByFlightIdAndSeatNumber(Long flightId, String seatNumber);
}
