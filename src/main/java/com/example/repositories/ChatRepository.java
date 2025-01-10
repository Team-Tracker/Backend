package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.models.Chat;

public interface ChatRepository extends CrudRepository<Chat, Integer> {

    @Query(value = "SELECT * FROM chat WHERE user_id = ?1", nativeQuery = true)
    public Iterable<Chat> findByUserId(Integer userId);
}