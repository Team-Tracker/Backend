package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.models.Chat;

public interface ChatRepository extends CrudRepository<Chat, Integer> {

    Iterable<Chat> findByUser_id(Integer user_id);
}