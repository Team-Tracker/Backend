package io.github.teamtracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.teamtracker.model.scrum.Assignee;
import io.github.teamtracker.model.scrum.Board;
import io.github.teamtracker.model.scrum.Sprint;
import io.github.teamtracker.model.scrum.Task;
import io.github.teamtracker.repository.scrum.AssigneeRepository;
import io.github.teamtracker.repository.scrum.BoardRepository;
import io.github.teamtracker.repository.scrum.SprintRepository;
import io.github.teamtracker.repository.scrum.TaskRepository;

@Service
@Transactional
public class ScrumService {

    private final SprintRepository sprintRepository;
    private final BoardRepository boardRepository;
    private final TaskRepository taskRepository;
    private final AssigneeRepository assigneeRepository;

    public ScrumService(SprintRepository sprintRepository,
            BoardRepository boardRepository,
            TaskRepository taskRepository,
            AssigneeRepository assigneeRepository) {
        this.sprintRepository = sprintRepository;
        this.boardRepository = boardRepository;
        this.taskRepository = taskRepository;
        this.assigneeRepository = assigneeRepository;
    }

    // Sprint-Methoden
    public Iterable<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    public Optional<Sprint> getSprintById(Integer id) {
        return sprintRepository.findById(id);
    }

    public Sprint createSprint(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    public void deleteSprint(Integer id) {
        sprintRepository.deleteById(id);
    }

    // Board-Methoden
    public Iterable<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoardById(Integer id) {
        return boardRepository.findById(id);
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public void deleteBoard(Integer id) {
        boardRepository.deleteById(id);
    }

    // Task-Methoden
    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Integer id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    // Assignee-Methoden
    public Iterable<Assignee> getAllAssignees() {
        return assigneeRepository.findAll();
    }

    public Optional<Assignee> getAssigneeById(Integer id) {
        return assigneeRepository.findById(id);
    }

    public Assignee assignTask(Assignee assignee) {
        return assigneeRepository.save(assignee);
    }

    public void removeAssignee(Integer id) {
        assigneeRepository.deleteById(id);
    }
}