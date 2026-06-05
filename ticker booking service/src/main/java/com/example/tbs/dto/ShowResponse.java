package com.example.tbs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowResponse {
    private Long showId;
    private int totalSeats;
    private String name;
    private int availableSeats;
    private String venue;
    private LocalDateTime showTime;
}
