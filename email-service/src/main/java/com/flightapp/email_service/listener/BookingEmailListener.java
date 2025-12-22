package com.flightapp.email_service.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.flightapp.email_service.config.RabbitMQConfig;
import com.flightapp.email_service.event.BookingConfirmedEvent;

@Component
public class BookingEmailListener {

    private final JavaMailSender mailSender;

    public BookingEmailListener(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void sendBookingConfirmation(BookingConfirmedEvent event) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getUserEmail());
        message.setSubject("Flight Booking Confirmed");
        message.setText(
                "Your booking is confirmed!\n\n" +
                "Booking ID: " + event.getBookingId() + "\n" +
                "Flight ID: " + event.getFlightId() + "\n" +
                "Seats Booked: " + event.getSeatsBooked()
        );

        mailSender.send(message);
    }
}
