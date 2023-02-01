package com.repositories;

import com.entities.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface TournamentRepository extends PagingAndSortingRepository<Tournament, Integer> {

    Page<Tournament> findByManager(int managerId, Pageable pageable);

    Page<Tournament> findByPriceLessThan(int maxPrice, Pageable pageable);

    Page<Tournament> findByDateTimeAfter(LocalDateTime dateTime, Pageable pageable);

    Page<Tournament> findByDateTimeBefore(LocalDateTime dateTime, Pageable pageable);

    Page<Tournament> findByDateTimeBetween(LocalDateTime dateTime1, LocalDateTime dateTime2, Pageable pageable);

    @Transactional
    @Query(value = "select tournament_tournament_id from player_tournaments where player_id =: playerId", nativeQuery = true)
    Page<Tournament> getTournamentsByPlayerId(@Param("playerId") int playerId, Pageable pageable);

    Tournament getById(int tournamentId);
}
