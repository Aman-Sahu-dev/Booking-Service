package com.example.tbs.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowRequest {
    private String showName;
    private int totalSeats;
    private String venue;
    private LocalDateTime showTime;
}
