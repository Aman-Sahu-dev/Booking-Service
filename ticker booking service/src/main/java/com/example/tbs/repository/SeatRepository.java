package com.example.tbs.repository;

import com.example.tbs.entity.Seat;
import com.example.tbs.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {
    Optional<Show> findByShowId(Long showId);
}
