package com.rest.api.entity.board;

import com.rest.api.entity.User;
import com.rest.api.entity.common.CommonDateEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Post extends CommonDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String title;
    private String content;
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Post(User user, Long boardId, String author, String title, String content) {
        this.user = user;
        this.boardId = boardId;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public Post setUpdate(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
        return this;
    }
}
