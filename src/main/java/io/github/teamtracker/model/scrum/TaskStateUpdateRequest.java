package io.github.teamtracker.model.scrum;

public class TaskStateUpdateRequest {
    private TaskState state;

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }
}