package com.rest.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicResult<T> extends CommonResult {
    private T data;
}