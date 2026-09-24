package com.faiz.taskflow.service;

import com.faiz.taskflow.entity.Board;
import com.faiz.taskflow.entity.BoardColumn;
import com.faiz.taskflow.entity.User;
import com.faiz.taskflow.repository.BoardColumnRepository;
import com.faiz.taskflow.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private static final String[] DEFAULT_COLUMNS = { "To Do", "In Progress", "Review", "Done" };

    private final BoardRepository boardRepository;
    private final BoardColumnRepository columnRepository;

    public BoardService(BoardRepository boardRepository, BoardColumnRepository columnRepository) {
        this.boardRepository = boardRepository;
        this.columnRepository = columnRepository;
    }

    public List<Board> getBoardsForUser(Long userId) {
        return boardRepository.findByOwnerIdOrderByCreatedAtAsc(userId);
    }

    public Board createBoard(String name, User owner) {
        Board board = new Board(name, owner);
        board = boardRepository.save(board);

        for (int i = 0; i < DEFAULT_COLUMNS.length; i++) {
            columnRepository.save(new BoardColumn(DEFAULT_COLUMNS[i], i, board));
        }
        return board;
    }

    public Board getBoard(Long boardId, Long userId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        if (!board.getOwner().getId().equals(userId)) {
            throw new SecurityException("You do not have access to this board");
        }
        return board;
    }

    public void deleteBoard(Long boardId, Long userId) {
        Board board = getBoard(boardId, userId);
        boardRepository.delete(board);
    }

    public Board renameBoard(Long boardId, Long userId, String newName) {
        Board board = getBoard(boardId, userId);
        board.setName(newName);
        return boardRepository.save(board);
    }
}
