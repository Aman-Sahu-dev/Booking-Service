package com.example.tbs.controller;

import com.example.tbs.dto.SeatRequest;
import com.example.tbs.dto.SeatResponse;
import com.example.tbs.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SeatController {
    private final SeatService seatService;

    @PostMapping("/seats")
    public ResponseEntity<SeatResponse> addSeat(@RequestBody SeatRequest request){
        return ResponseEntity.status(201).body(seatService.addSeat(request));
    }
    @DeleteMapping("/seats/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable Long id){
        seatService.deleteSeat(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/seats/{id}")
    public ResponseEntity<List<SeatResponse>> getSeatByShowId(@PathVariable Long id){
        return ResponseEntity.ok(seatService.getSeatsByShow(id));
    }
}
