package io.github.teamtracker.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.teamtracker.exception.ScrumException;
import io.github.teamtracker.model.scrum.Assignee;
import io.github.teamtracker.model.scrum.Board;
import io.github.teamtracker.model.scrum.Sprint;
import io.github.teamtracker.model.scrum.Task;
import io.github.teamtracker.service.ScrumService;

@RestController
@RequestMapping("/scrum")
public class ScrumController {

    private final ScrumService scrumService;

    public ScrumController(ScrumService scrumService) {
        this.scrumService = scrumService;
    }

    // Sprints
    @GetMapping("/sprints")
    public Iterable<Sprint> getAllSprints() {
        return scrumService.getAllSprints();
    }

    @GetMapping("/sprints/{id}")
    public Sprint getSprintById(@PathVariable Integer id) {
        return scrumService.getSprintById(id)
                .orElseThrow(() -> new ScrumException("Sprint not found"));
    }

    @PostMapping("/sprints")
    public Sprint createSprint(@RequestBody Sprint sprint) {
        return scrumService.createSprint(sprint);
    }

    @DeleteMapping("/sprints/{id}")
    public void deleteSprint(@PathVariable Integer id) {
        scrumService.deleteSprint(id);
    }

    // Boards
    @GetMapping("/boards")
    public Iterable<Board> getAllBoards() {
        return scrumService.getAllBoards();
    }

    @GetMapping("/boards/{id}")
    public Board getBoardById(@PathVariable Integer id) {
        return scrumService.getBoardById(id)
                .orElseThrow(() -> new ScrumException("Board not found"));
    }

    @PostMapping("/boards")
    public Board createBoard(@RequestBody Board board) {
        return scrumService.createBoard(board);
    }

    @DeleteMapping("/boards/{id}")
    public void deleteBoard(@PathVariable Integer id) {
        scrumService.deleteBoard(id);
    }

    // Tasks
    @GetMapping("/tasks")
    public Iterable<Task> getAllTasks() {
        return scrumService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Integer id) {
        return scrumService.getTaskById(id)
                .orElseThrow(() -> new ScrumException("Task not found"));
    }

    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        return scrumService.createTask(task);
    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Integer id) {
        scrumService.deleteTask(id);
    }

    // Assignees
    @GetMapping("/assignees")
    public Iterable<Assignee> getAllAssignees() {
        return scrumService.getAllAssignees();
    }

    @GetMapping("/assignees/{id}")
    public Assignee getAssigneeById(@PathVariable Integer id) {
        return scrumService.getAssigneeById(id)
                .orElseThrow(() -> new ScrumException("Assignee not found"));
    }

    @PostMapping("/assignees")
    public Assignee assignTask(@RequestBody Assignee assignee) {
        return scrumService.assignTask(assignee);
    }

    @DeleteMapping("/assignees/{id}")
    public void removeAssignee(@PathVariable Integer id) {
        scrumService.removeAssignee(id);
    }
}