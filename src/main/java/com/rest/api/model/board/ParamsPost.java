package com.rest.api.model.board;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class ParamsPost {
    @NotEmpty
    @ApiModelProperty(value = "작성자명", required = true)
    private String author;
    @NotEmpty
    @ApiModelProperty(value = "제목", required = true)
    private String title;
    @NotEmpty
    @ApiModelProperty(value = "내용", required = true)
    private String content;
}
