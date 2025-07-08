package io.github.teamtracker.repository.scrum;

import io.github.teamtracker.model.scrum.Sprint;
import org.springframework.data.repository.CrudRepository;

public interface SprintRepository extends CrudRepository<Sprint, Integer> {
}