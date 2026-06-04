package com.example.tbs.dto;

import com.example.tbs.entity.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeatResponse {
    private Long seatId;
    private String seatNumber;
    private Long showId;
    private SeatStatus status;

}
