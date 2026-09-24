package com.faiz.taskflow.service;

import com.faiz.taskflow.dto.DashboardStats;
import com.faiz.taskflow.entity.Task;
import com.faiz.taskflow.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DashboardService {

    private final TaskRepository taskRepository;

    public DashboardService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public DashboardStats getStats(Long userId) {
        List<Task> allTasks = taskRepository.findAllByOwner(userId);

        long total = allTasks.size();
        long completed = allTasks.stream()
                .filter(t -> "Done".equalsIgnoreCase(t.getColumn().getName()))
                .count();
        long overdue = allTasks.stream().filter(Task::isOverdue).count();
        LocalDate weekAgo = LocalDate.now().minusDays(7);
        long thisWeek = allTasks.stream()
                .filter(t -> t.getCreatedAt() != null &&
                        ChronoUnit.DAYS.between(t.getCreatedAt().toLocalDate(), LocalDate.now()) <= 7)
                .count();

        double percentage = total == 0 ? 0 : Math.round((completed * 1000.0 / total)) / 10.0;

        return new DashboardStats(total, completed, overdue, thisWeek, percentage);
    }
}
