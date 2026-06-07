package com.example.tbs.service;

import com.example.tbs.dto.BookingRequest;
import com.example.tbs.dto.BookingResponse;
import com.example.tbs.entity.*;
import com.example.tbs.repository.BookingRepository;
import com.example.tbs.repository.SeatRepository;
import com.example.tbs.repository.ShowRepository;
import com.example.tbs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final KafkaProducerService kafkaProducerService;

    private BookingResponse toResponse(Booking booking){
        return BookingResponse.builder()
                .username(booking.getUser().getName())
                .seaNumber(booking.getSeat().getSeatNumber())
                .showName(booking.getShow().getName())
                .status(booking.getStatus())
                .bookedAt(booking.getCreatedAt())
                .bookingId(booking.getId())
                .build();
    }

    @Transactional
    public BookingResponse bookShow(BookingRequest request){
        User user = userRepository.findById(request.getUserId()).orElseThrow(()-> new RuntimeException("user not found"));
        Seat seat = seatRepository.findById(request.getSeatId()).orElseThrow(()-> new RuntimeException("seat not found"));
        Show show = showRepository.findById(request.getShowId()).orElseThrow(()-> new RuntimeException("show not found"));
        if(seat.getStatus() == SeatStatus.BOOKED){
            throw new RuntimeException("seat not available");
        }
        seat.setStatus(SeatStatus.BOOKED);
        show.setAvailableSeats(show.getAvailableSeats()-1);

        seatRepository.save(seat);
        showRepository.save(show);
        Booking booking = Booking.builder()
                .show(show)
                .seat(seat)
                .status(BookingStatus.CONFIRMED)
                .user(user)
                .build();
        bookingRepository.save(booking);
        kafkaProducerService.sendBookingEvent(
                "BOOKING_CONFIRMED::" + booking.getId() +
                        "::USER::" + booking.getUser().getName() +
                        "::SEAT::" + booking.getSeat().getSeatNumber() +
                        "::SHOW::" + booking.getShow().getName()
        );
        return toResponse(booking);
    }
    @Transactional
    public BookingResponse getBooking(Long id){
        Booking booking = bookingRepository.findById(id).orElseThrow(()-> new RuntimeException("booking not found"));
        return toResponse(booking);
    }

    @Transactional
    public void cancelBooking(Long id){
        Booking booking = bookingRepository.findById(id).orElseThrow(()-> new RuntimeException("booking not found"));
        booking.setStatus(BookingStatus.CANCELLED);
        booking.getSeat().setStatus(SeatStatus.AVAILABLE);
        booking.getShow().setAvailableSeats(booking.getShow().getAvailableSeats()+1);

        bookingRepository.save(booking);

    }

}
