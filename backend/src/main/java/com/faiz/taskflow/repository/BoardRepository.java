package com.faiz.taskflow.repository;

import com.faiz.taskflow.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByOwnerIdOrderByCreatedAtAsc(Long ownerId);
}
