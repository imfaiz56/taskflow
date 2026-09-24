package com.faiz.taskflow.controller;

import com.faiz.taskflow.dto.CreateBoardRequest;
import com.faiz.taskflow.entity.Board;
import com.faiz.taskflow.entity.User;
import com.faiz.taskflow.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public ResponseEntity<List<Board>> getBoards(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(boardService.getBoardsForUser(user.getId()));
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@AuthenticationPrincipal User user,
                                              @Valid @RequestBody CreateBoardRequest request) {
        return ResponseEntity.ok(boardService.createBoard(request.getName(), user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoard(@AuthenticationPrincipal User user, @PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoard(id, user.getId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> renameBoard(@AuthenticationPrincipal User user, @PathVariable Long id,
                                              @Valid @RequestBody CreateBoardRequest request) {
        return ResponseEntity.ok(boardService.renameBoard(id, user.getId(), request.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@AuthenticationPrincipal User user, @PathVariable Long id) {
        boardService.deleteBoard(id, user.getId());
        return ResponseEntity.noContent().build();
    }
}
