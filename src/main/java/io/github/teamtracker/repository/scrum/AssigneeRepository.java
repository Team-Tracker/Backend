package io.github.teamtracker.repository.scrum;

import io.github.teamtracker.model.scrum.Assignee;
import org.springframework.data.repository.CrudRepository;

public interface AssigneeRepository extends CrudRepository<Assignee, Integer> {
}