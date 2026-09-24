package com.faiz.taskflow.controller;

import com.faiz.taskflow.dto.AddSubtaskRequest;
import com.faiz.taskflow.entity.Subtask;
import com.faiz.taskflow.service.SubtaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SubtaskController {

    private final SubtaskService subtaskService;

    public SubtaskController(SubtaskService subtaskService) {
        this.subtaskService = subtaskService;
    }

    @GetMapping("/tasks/{taskId}/subtasks")
    public ResponseEntity<List<Subtask>> getSubtasks(@PathVariable Long taskId) {
        return ResponseEntity.ok(subtaskService.getSubtasks(taskId));
    }

    @PostMapping("/tasks/{taskId}/subtasks")
    public ResponseEntity<Subtask> addSubtask(@PathVariable Long taskId, @Valid @RequestBody AddSubtaskRequest request) {
        return ResponseEntity.ok(subtaskService.addSubtask(taskId, request.getTitle()));
    }

    @PatchMapping("/subtasks/{id}/toggle")
    public ResponseEntity<Subtask> toggleSubtask(@PathVariable Long id) {
        return ResponseEntity.ok(subtaskService.toggleSubtask(id));
    }

    @DeleteMapping("/subtasks/{id}")
    public ResponseEntity<Void> deleteSubtask(@PathVariable Long id) {
        subtaskService.deleteSubtask(id);
        return ResponseEntity.noContent().build();
    }
}
