package io.github.teamtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.teamtracker.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
