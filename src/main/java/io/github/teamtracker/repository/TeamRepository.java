package io.github.teamtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.teamtracker.model.team.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
