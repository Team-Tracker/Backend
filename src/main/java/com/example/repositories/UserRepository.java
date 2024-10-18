package com.example.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM user WHERE username = ?1", nativeQuery = true)
    Iterable<User> findByUsername(String username);

    @Query(value = "SELECT username FROM user WHERE id = ?1", nativeQuery = true)
    Iterable<String> resolveUsername(Integer id);
}