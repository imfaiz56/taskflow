package com.faiz.taskflow.repository;

import com.faiz.taskflow.entity.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardColumnRepository extends JpaRepository<BoardColumn, Long> {
    List<BoardColumn> findByBoardIdOrderByPositionAsc(Long boardId);
}
