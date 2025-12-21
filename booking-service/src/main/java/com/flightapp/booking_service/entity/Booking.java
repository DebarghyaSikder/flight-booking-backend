package com.flightapp.booking_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long flightId;

    private String userEmail;

    private int seatsBooked;

    private double totalAmount;

    private String status; // PENDING, CONFIRMED, CANCELLED

    private LocalDateTime bookingTime;

    public Booking() {}

    @PrePersist
    public void onCreate() {
        bookingTime = LocalDateTime.now();
        status = "PENDING";
    }

    // getters and setters
    public Long getId() { return id; }

    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }

    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }

    public int getSeatsBooked() { return seatsBooked; }
    public void setSeatsBooked(int seatsBooked) { this.seatsBooked = seatsBooked; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getBookingTime() { return bookingTime; }
}
