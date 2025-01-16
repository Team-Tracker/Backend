package io.github.teamtracker.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.teamtracker.model.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}