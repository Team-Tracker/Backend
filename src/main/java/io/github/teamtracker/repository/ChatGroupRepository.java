package io.github.teamtracker.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.teamtracker.model.chat.ChatGroup;

public interface ChatGroupRepository extends CrudRepository<ChatGroup, Integer> {
}