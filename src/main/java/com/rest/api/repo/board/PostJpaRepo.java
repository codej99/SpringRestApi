package com.rest.api.repo.board;

import com.rest.api.entity.board.Board;
import com.rest.api.entity.board.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostJpaRepo extends JpaRepository<Post, Long> {
    List<Post> findByBoard(Board board);
}