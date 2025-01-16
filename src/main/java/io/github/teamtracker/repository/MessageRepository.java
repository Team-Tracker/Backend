package io.github.teamtracker.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.github.teamtracker.model.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Query(value = "SELECT * FROM message WHERE id = ?1", nativeQuery = true)
    Iterable<Message> findByMessage_id(Integer message_id);
}