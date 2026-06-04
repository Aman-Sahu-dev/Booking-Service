package com.example.tbs.dto;

import com.example.tbs.entity.SeatStatus;
import lombok.Data;

@Data
public class SeatRequest {
    private String seatNumber;
    private Long showId;
}
