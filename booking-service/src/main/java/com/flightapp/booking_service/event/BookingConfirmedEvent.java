package com.flightapp.booking_service.event;

public class BookingConfirmedEvent {

    private Long bookingId;
    private String userEmail;
    private Long flightId;
    private int seatsBooked;

    public BookingConfirmedEvent() {}

    public BookingConfirmedEvent(Long bookingId, String userEmail,
                                 Long flightId, int seatsBooked) {
        this.bookingId = bookingId;
        this.userEmail = userEmail;
        this.flightId = flightId;
        this.seatsBooked = seatsBooked;
    }

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public int getSeatsBooked() {
		return seatsBooked;
	}

	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}

    // getters & setters
    
    
}