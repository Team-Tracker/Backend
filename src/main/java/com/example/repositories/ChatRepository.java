package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.models.Chat;

public interface ChatRepository extends CrudRepository<Chat, Integer> {

    @Query(value = "SELECT * FROM chats WHERE user_id = ?1", nativeQuery = true)
    public Iterable<Chat> findByUser_id(Integer user_id);
}