package com.flightapp.booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.flightapp.booking_service.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByUserEmail(String userEmail);
}
