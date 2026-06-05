package com.example.tbs.service;

import com.example.tbs.dto.ShowRequest;
import com.example.tbs.dto.ShowResponse;
import com.example.tbs.entity.Show;
import com.example.tbs.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowRepository showRepository;

    private ShowResponse toResponse(Show show){
        return ShowResponse.builder()
                .name(show.getName())
                .venue(show.getVenue())
                .showTime(show.getDateTime())
                .availableSeats(show.getAvailableSeats())
                .totalSeats(show.getTotalSeats())
                .build();
    }


    public ShowResponse addShow(ShowRequest request){
        Show show = Show.builder()
                .name(request.getShowName())
                .venue(request.getVenue())
                .dateTime(request.getShowTime())
                .totalSeats(request.getTotalSeats())
                .availableSeats(request.getAvailableSeats())
                .build();
        showRepository.save(show);
        return toResponse(show);
    }
    public void removeShow(Long id){
        Show show = showRepository.findById(id).orElseThrow(()-> new RuntimeException("show not found"));
        showRepository.delete(show);
    }
    public ShowResponse updateShow(Long id,ShowRequest request){
        Show show = showRepository.findById(id).orElseThrow(()-> new RuntimeException("show not found"));
        show.setName(request.getShowName());
        show.setVenue(request.getVenue());
        show.setAvailableSeats(request.getAvailableSeats());
        show.setDateTime(request.getShowTime());
        show.setTotalSeats(request.getTotalSeats());

        showRepository.save(show);
        return toResponse(show);
    }

    public List<ShowResponse> getAllShows(){
      return   showRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }
}
