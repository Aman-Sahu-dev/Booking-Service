package com.example.ns;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationConsumer {

    @KafkaListener(topics = "ticket-booking", groupId = "notification-group")
    public void handleBookingEvent(String message){
        log.info("Notification received: {}",message);
        processNotification(message);
    }
    private void processNotification(String message){
        String [] parts = message.split("::");
        if(parts.length >= 8){
            String bookingId = parts[1];
            String userName = parts[3];
            String seatNumber = parts[5];
            String showName = parts[7];

            log.info("Processing ticket for booking #{}", bookingId);
            log.info("User: {} | Seat: {} | Show: {}", userName, seatNumber, showName);
            log.info("Ticket generated successfully for {}", userName);
        }
    }
}
