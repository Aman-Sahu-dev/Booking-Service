package com.example.tbs.service;

import com.example.tbs.dto.SeatRequest;
import com.example.tbs.dto.SeatResponse;
import com.example.tbs.entity.Seat;
import com.example.tbs.entity.SeatStatus;
import com.example.tbs.entity.Show;
import com.example.tbs.repository.SeatRepository;
import com.example.tbs.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {
    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;
    private SeatResponse toResponse(Seat seat){
        return SeatResponse.builder()
                .seatId(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .status(seat.getStatus())
                .showId(seat.getShow().getId())
                .build();
    }
    public SeatResponse addSeat(SeatRequest request){
        Show show = showRepository.findById(request.getShowId()).orElseThrow(()-> new RuntimeException("show not found"));
        Seat seat = Seat.builder()
                .seatNumber(request.getSeatNumber())
                .show(show)
                .status(SeatStatus.AVAILABLE)
                .build();
        seatRepository.save(seat);
        return toResponse(seat);
    }
    public void deleteSeat( Long seatId){
        Seat seat = seatRepository.findById(seatId).orElseThrow(()-> new RuntimeException("seat not found"));
        seatRepository.delete(seat);
    }
    public List<SeatResponse> getSeatsByShow(Long showId){
        return seatRepository.findByShowId(showId).stream()
                .map(this::toResponse)
                .toList();
    }

}
