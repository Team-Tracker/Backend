package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}