package com.faiz.taskflow.service;

import com.faiz.taskflow.entity.BoardColumn;
import com.faiz.taskflow.entity.Tag;
import com.faiz.taskflow.entity.Task;
import com.faiz.taskflow.repository.BoardColumnRepository;
import com.faiz.taskflow.repository.TagRepository;
import com.faiz.taskflow.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final BoardColumnRepository columnRepository;
    private final TagRepository tagRepository;

    public TaskService(TaskRepository taskRepository, BoardColumnRepository columnRepository,
                        TagRepository tagRepository) {
        this.taskRepository = taskRepository;
        this.columnRepository = columnRepository;
        this.tagRepository = tagRepository;
    }

    public List<Task> getTasksForColumn(Long columnId) {
        return taskRepository.findByColumnIdOrderByPositionAsc(columnId);
    }

    public Task createTask(Long columnId, Task incoming, List<String> tagNames) {
        BoardColumn column = columnRepository.findById(columnId)
                .orElseThrow(() -> new IllegalArgumentException("Column not found"));

        int nextPosition = taskRepository.findByColumnIdOrderByPositionAsc(columnId).size();

        Task task = new Task();
        task.setTitle(incoming.getTitle());
        task.setDescription(incoming.getDescription());
        task.setPriority(incoming.getPriority() != null ? incoming.getPriority() : Task.Priority.MEDIUM);
        task.setDueDate(incoming.getDueDate());
        task.setPosition(nextPosition);
        task.setColumn(column);
        task.setTags(resolveTags(tagNames));

        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId, Task updates, List<String> tagNames) {
        Task task = getTaskOrThrow(taskId);
        if (updates.getTitle() != null) task.setTitle(updates.getTitle());
        if (updates.getDescription() != null) task.setDescription(updates.getDescription());
        if (updates.getPriority() != null) task.setPriority(updates.getPriority());
        task.setDueDate(updates.getDueDate());
        if (tagNames != null) task.setTags(resolveTags(tagNames));
        return taskRepository.save(task);
    }

    public Task moveTask(Long taskId, Long targetColumnId, Integer newPosition) {
        Task task = getTaskOrThrow(taskId);
        BoardColumn targetColumn = columnRepository.findById(targetColumnId)
                .orElseThrow(() -> new IllegalArgumentException("Target column not found"));

        task.setColumn(targetColumn);
        task.setPosition(newPosition != null ? newPosition
                : taskRepository.findByColumnIdOrderByPositionAsc(targetColumnId).size());

        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    private Task getTaskOrThrow(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
    }

    private Set<Tag> resolveTags(List<String> tagNames) {
        if (tagNames == null) return Set.of();
        return tagNames.stream()
                .map(name -> tagRepository.findByNameIgnoreCase(name)
                        .orElseGet(() -> tagRepository.save(new Tag(name, "#49C5B6"))))
                .collect(Collectors.toSet());
    }
}
