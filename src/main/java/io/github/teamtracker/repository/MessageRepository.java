package io.github.teamtracker.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.github.teamtracker.model.chat.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Query(value = "SELECT m.* FROM message m JOIN chat c ON m.chat_group_id = c.chat_group_id WHERE c.user_id = ?1", nativeQuery = true)
    public Iterable<Message> findByUserId(Integer userId);
}