package com.example.tbs.dto;

import com.example.tbs.entity.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingResponse {
    private Long bookingId;
    private String username;
    private String showName;
    private String seaNumber;
    private BookingStatus status;
    private LocalDateTime bookedAt;
}
