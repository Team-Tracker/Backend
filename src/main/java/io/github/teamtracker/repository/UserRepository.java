package io.github.teamtracker.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import io.github.teamtracker.model.user.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "SELECT * FROM user WHERE username = ?1", nativeQuery = true)
    User findByUsername(String username);

    @Query(value = "SELECT username FROM user WHERE id = ?1", nativeQuery = true)
    String resolveUsername(Integer id);

    @Query(value = "SELECT id FROM user WHERE username = ?1", nativeQuery = true)
    Integer resolveId(String username);
}