package com.faiz.taskflow.repository;

import com.faiz.taskflow.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByColumnIdOrderByPositionAsc(Long columnId);

    @org.springframework.data.jpa.repository.Query(
        "SELECT t FROM Task t WHERE t.column.board.owner.id = :userId"
    )
    List<Task> findAllByOwner(Long userId);
}
