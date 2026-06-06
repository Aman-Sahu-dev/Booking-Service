package com.example.tbs.controller;

import com.example.tbs.dto.BookingRequest;
import com.example.tbs.dto.BookingResponse;
import com.example.tbs.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/bookings")
    public ResponseEntity<BookingResponse> book(@RequestBody BookingRequest request){
        return ResponseEntity.status(201).body(bookingService.bookShow(request));
    }
    @PatchMapping("/bookings/{id}/cancel")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id){
        bookingService.cancelBooking(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/bookings/{id}")
    public ResponseEntity<BookingResponse> getBooking(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getBooking(id));
    }
}
