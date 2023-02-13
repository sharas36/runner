package com.repositories;

import com.entities.Manager;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ManagerRepository extends PagingAndSortingRepository<Manager, String> {

    public Manager getByEmail(String email);

    public List<Manager> findByEmail(String email);

    Manager getById(int id);

    @Transactional
    @Modifying
    @Query(value = "delete from managers where manager_id =:manager_id", nativeQuery = true)
    void deleteManager(@Param("manager_id") int manager_Id);
}