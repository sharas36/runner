package com.repositories;

import com.entities.Manager;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ManagerRepository extends PagingAndSortingRepository<Manager, String> {

    public Manager getByEmail(String email);

    public List<Manager> findByEmail(String email);

    Integer getById(int id);
}
