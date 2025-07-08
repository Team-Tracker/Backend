package io.github.teamtracker.repository.scrum;

import io.github.teamtracker.model.scrum.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {
}