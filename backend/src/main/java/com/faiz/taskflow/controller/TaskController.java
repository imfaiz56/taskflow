package com.faiz.taskflow.controller;

import com.faiz.taskflow.dto.CreateTaskRequest;
import com.faiz.taskflow.dto.MoveTaskRequest;
import com.faiz.taskflow.entity.Task;
import com.faiz.taskflow.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/columns/{columnId}/tasks")
    public ResponseEntity<List<Task>> getTasks(@PathVariable Long columnId) {
        return ResponseEntity.ok(taskService.getTasksForColumn(columnId));
    }

    @PostMapping("/columns/{columnId}/tasks")
    public ResponseEntity<Task> createTask(@PathVariable Long columnId, @Valid @RequestBody CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        return ResponseEntity.ok(taskService.createTask(columnId, task, request.getTags()));
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody CreateTaskRequest request) {
        Task updates = new Task();
        updates.setTitle(request.getTitle());
        updates.setDescription(request.getDescription());
        updates.setPriority(request.getPriority());
        updates.setDueDate(request.getDueDate());
        return ResponseEntity.ok(taskService.updateTask(id, updates, request.getTags()));
    }

    @PatchMapping("/tasks/{id}/move")
    public ResponseEntity<Task> moveTask(@PathVariable Long id, @RequestBody MoveTaskRequest request) {
        return ResponseEntity.ok(taskService.moveTask(id, request.getTargetColumnId(), request.getNewPosition()));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
