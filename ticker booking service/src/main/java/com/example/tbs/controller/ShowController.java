package com.example.tbs.controller;

import com.example.tbs.dto.ShowRequest;
import com.example.tbs.dto.ShowResponse;
import com.example.tbs.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShowController {
    private final ShowService showService;

    @PostMapping("/shows")
    public ResponseEntity<ShowResponse> addShow(@RequestBody ShowRequest request){
        return ResponseEntity.status(201).body(showService.addShow(request));
    }
    @PatchMapping("/shows/{id}")
    public ResponseEntity<ShowResponse> updateShow(@PathVariable Long id , @RequestBody ShowRequest request){
        return ResponseEntity.ok(showService.updateShow(id,request));
    }
    @GetMapping("/shows")
    public ResponseEntity<List<ShowResponse>> getAllShows(){
        return ResponseEntity.ok(showService.getAllShows());
    }
    @DeleteMapping("/shows/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id){
        showService.removeShow(id);
        return ResponseEntity.noContent().build();
    }


}
