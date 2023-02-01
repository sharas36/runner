package com.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface DealerRepository extends PagingAndSortingRepository<Dealer, String> {
    Dealer getById(int dealerId);
}
