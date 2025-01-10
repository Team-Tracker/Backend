package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.models.ChatGroup;

public interface ChatGroupRepository extends CrudRepository<ChatGroup, Integer> {
}