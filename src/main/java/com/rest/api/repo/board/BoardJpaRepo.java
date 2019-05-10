package com.rest.api.repo.board;

import com.rest.api.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepo extends JpaRepository<Board, Long> {
    Board findByName(String name);
}
