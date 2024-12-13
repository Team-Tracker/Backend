package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.models.Team;

public interface TeamRepository extends CrudRepository<Team, Integer> {

    @Query(value = "SELECT team_id FROM team WHERE user_id = ?1", nativeQuery = true)
    Iterable<Team> findByUser_id(Integer user_id);
}