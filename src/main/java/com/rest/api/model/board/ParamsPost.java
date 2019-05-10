package com.rest.api.model.board;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class ParamsPost {
    @NotEmpty
    @Max(50)
    @ApiModelProperty(value = "작성자명", required = true)
    private String author;
    @NotEmpty
    @Max(100)
    @ApiModelProperty(value = "제목", required = true)
    private String title;
    @Max(500)
    @ApiModelProperty(value = "내용", required = true)
    private String content;
}
