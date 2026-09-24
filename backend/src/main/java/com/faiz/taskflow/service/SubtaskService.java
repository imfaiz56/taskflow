package com.faiz.taskflow.service;

import com.faiz.taskflow.entity.Subtask;
import com.faiz.taskflow.entity.Task;
import com.faiz.taskflow.repository.SubtaskRepository;
import com.faiz.taskflow.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubtaskService {

    private final SubtaskRepository subtaskRepository;
    private final TaskRepository taskRepository;

    public SubtaskService(SubtaskRepository subtaskRepository, TaskRepository taskRepository) {
        this.subtaskRepository = subtaskRepository;
        this.taskRepository = taskRepository;
    }

    public List<Subtask> getSubtasks(Long taskId) {
        return subtaskRepository.findByTaskId(taskId);
    }

    public Subtask addSubtask(Long taskId, String title) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        return subtaskRepository.save(new Subtask(title, task));
    }

    public Subtask toggleSubtask(Long subtaskId) {
        Subtask subtask = subtaskRepository.findById(subtaskId)
                .orElseThrow(() -> new IllegalArgumentException("Subtask not found"));
        subtask.setDone(!subtask.isDone());
        return subtaskRepository.save(subtask);
    }

    public void deleteSubtask(Long subtaskId) {
        subtaskRepository.deleteById(subtaskId);
    }
}
