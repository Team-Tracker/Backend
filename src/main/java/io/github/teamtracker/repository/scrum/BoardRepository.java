package io.github.teamtracker.repository.scrum;

import io.github.teamtracker.model.scrum.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Integer> {
}