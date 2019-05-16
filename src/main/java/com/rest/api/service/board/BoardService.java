package com.rest.api.service.board;

import com.rest.api.advice.exception.CNotOwnerException;
import com.rest.api.advice.exception.CResourceNotExistException;
import com.rest.api.advice.exception.CUserNotFoundException;
import com.rest.api.entity.User;
import com.rest.api.entity.board.Board;
import com.rest.api.entity.board.Post;
import com.rest.api.model.board.ParamsPost;
import com.rest.api.repo.UserJpaRepo;
import com.rest.api.repo.board.BoardJpaRepo;
import com.rest.api.repo.board.PostJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardJpaRepo boardJpaRepo;
    private final PostJpaRepo postJpaRepo;
    private final UserJpaRepo userJpaRepo;

    // 게시판 이름으로 게시판을 조회. 없을경우 CResourceNotExistException 처리
    public Board findBoard(String boardName) {
        return Optional.ofNullable(boardJpaRepo.findByName(boardName)).orElseThrow(CResourceNotExistException::new);
    }

    // 게시판 이름으로 게시물 리스트 조회.
    public List<Post> findPosts(String boardName) {
        return postJpaRepo.findByBoard(findBoard(boardName));
    }

    // 게시물ID로 게시물 단건 조회. 없을경우 CResourceNotExistException 처리
    public Post getPost(long postId) {
        return postJpaRepo.findById(postId).orElseThrow(CResourceNotExistException::new);
    }

    // 게시물을 등록합니다. 게시물의 회원UID가 조회되지 않으면 CUserNotFoundException 처리합니다.
    public Post writePost(String uid, String boardName, ParamsPost paramsPost) {
        Board board = findBoard(boardName);
        Post post = new Post(userJpaRepo.findByUid(uid).orElseThrow(CUserNotFoundException::new), board, paramsPost.getAuthor(), paramsPost.getTitle(), paramsPost.getContent());
        return postJpaRepo.save(post);
    }

    // 게시물을 수정합니다. 게시물 등록자와 로그인 회원정보가 틀리면 CNotOwnerException 처리합니다.
    public Post updatePost(long postId, String uid, ParamsPost paramsPost) {
        Post post = getPost(postId);
        User user = post.getUser();
        if (!uid.equals(user.getUid()))
            throw new CNotOwnerException();

        post.setUpdate(paramsPost.getAuthor(), paramsPost.getTitle(), paramsPost.getContent());
        return post;
    }

    // 게시물을 삭제합니다. 게시물 등록자와 로그인 회원정보가 틀리면 CNotOwnerException 처리합니다.
    public boolean deletePost(long postId, String uid) {
        Post post = getPost(postId);
        User user = post.getUser();
        if (!uid.equals(user.getUid()))
            throw new CNotOwnerException();
        postJpaRepo.delete(post);
        return true;
    }
}
