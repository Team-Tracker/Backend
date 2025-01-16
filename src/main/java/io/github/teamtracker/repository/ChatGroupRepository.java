package io.github.teamtracker.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.teamtracker.model.ChatGroup;

public interface ChatGroupRepository extends CrudRepository<ChatGroup, Integer> {
}