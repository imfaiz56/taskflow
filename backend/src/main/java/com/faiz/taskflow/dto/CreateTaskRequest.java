package com.faiz.taskflow.dto;

import com.faiz.taskflow.entity.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class CreateTaskRequest {
    @NotBlank(message = "Task title is required")
    @Size(max = 150, message = "Title must be under 150 characters")
    private String title;

    @Size(max = 2000, message = "Description must be under 2000 characters")
    private String description;

    private Task.Priority priority;
    private LocalDate dueDate;
    private List<String> tags;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Task.Priority getPriority() { return priority; }
    public void setPriority(Task.Priority priority) { this.priority = priority; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
}
