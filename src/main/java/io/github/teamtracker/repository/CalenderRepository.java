package io.github.teamtracker.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.github.teamtracker.model.calender.Event;

public interface CalenderRepository extends CrudRepository<Event, Integer> {

    @Query(value = "SELECT * FROM event WHERE user_id = ?1", nativeQuery = true)
    public Iterable<Event> findByUserId(Integer userId);
}