package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.models.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Query(value = "SELECT * FROM message WHERE id = ?1", nativeQuery = true)
    Iterable<Message> findByMessage_id(Integer message_id);
}