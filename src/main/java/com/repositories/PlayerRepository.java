package com.repositories;

import com.entities.Player;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PlayerRepository extends PagingAndSortingRepository<Player, String> {

    Player getById(int playerId);
}
