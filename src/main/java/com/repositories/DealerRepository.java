package com.repositories;

import com.entities.Dealer;
import com.entities.Player;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DealerRepository extends PagingAndSortingRepository<Dealer, String> {
    Dealer getById(int dealerId);
}
