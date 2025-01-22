package io.github.teamtracker.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.github.teamtracker.model.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Query(value = "SELECT m.*\n" +
            "FROM message m\n" +
            "JOIN chat c ON m.chatGroupId = c.chat_group_id\n" +
            "WHERE c.user_id = ?1;", nativeQuery = true)
    public Iterable<Message> findByUserId(Integer userId);
}