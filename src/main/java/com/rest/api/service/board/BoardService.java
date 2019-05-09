package com.rest.api.service.board;

import com.rest.api.entity.board.Board;
import com.rest.api.entity.board.Post;
import com.rest.api.model.board.ParamsPost;
import com.rest.api.repo.board.BoardJpaRepo;
import com.rest.api.repo.board.PostJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardJpaRepo boardJpaRepo;
    private final PostJpaRepo postJpaRepo;

    public Board findBoard(String boardName) {
        return boardJpaRepo.findByName(boardName);
    }

    public List<Post> findPosts(String boardName) {
        Board board = findBoard(boardName);
        return postJpaRepo.findByBoard(board);
    }

    private Post getPost(long postId) {
        return postJpaRepo.findById(postId).orElse(null);
    }

    public Post writePost(String boardName, ParamsPost paramsPost) {
        Board board = findBoard(boardName);
        Post post = board.write(paramsPost.getAuthor(), paramsPost.getTitle(), paramsPost.getContent());
        return postJpaRepo.save(post);
    }
}
