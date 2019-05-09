package com.rest.api.entity.board;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String author;
    private String title;
    private String content;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Board board;

    protected Post() {
    }

    protected Post(Board board, String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
