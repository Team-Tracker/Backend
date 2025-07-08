package io.github.teamtracker.repository;

import org.springframework.data.repository.CrudRepository;

import io.github.teamtracker.model.team.Team;

public interface TeamRepository extends CrudRepository<Team, Integer> {
}