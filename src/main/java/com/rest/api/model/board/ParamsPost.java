package com.rest.api.model.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class ParamsPost {
    @NotEmpty
    private String author;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    @Builder
    public ParamsPost(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
