package com.repositories;

import com.entities.Prize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PrizeRepository extends PagingAndSortingRepository<Prize, Integer> {

    Prize getById(int prizeId);

    @Transactional
    @Query(value = "select from prize_players_that_buy where player_id =: playerId", nativeQuery = true)
    public Page<Prize> getPrizesByPlayer(@Param("playerId") int playerId, Pageable pageable);
}
