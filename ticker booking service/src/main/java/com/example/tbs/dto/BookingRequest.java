package com.example.tbs.dto;

import lombok.Data;

@Data
public class BookingRequest {
    private Long showId;
    private Long userId;
    private Long seatId;
}
