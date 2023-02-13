package com.repositories;

import com.entities.Player;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlayerRepository extends PagingAndSortingRepository<Player, String> {

    Player getById(int playerId);

    List<Player> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "delete from players where player_id =:player_id", nativeQuery = true)
    void deletePlayer(@Param("player_id") int player_Id);

    @Transactional
    @Modifying
    @Query(value = "select from players where is_dealer = true", nativeQuery = true)
    List<Player> getAllDealers();
}
