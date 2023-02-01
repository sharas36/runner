package com.repositories;

import com.entities.Location;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LocationRepository extends PagingAndSortingRepository<Location, String> {

    Location findByLocationName(String locationName);
}
