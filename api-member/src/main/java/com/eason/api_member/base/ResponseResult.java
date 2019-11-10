package com.eason.api_member.base;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Copyright (C), 2019-2025, xingyun information technology wuxi Co., Ltd.
 *
 * @author Eason
 * @version 1.00
 * @date 2019/11/5 22:39
 */
@Data
@NoArgsConstructor
public class ResponseResult<T> {
    private Integer code;
    private String message;
    private T data;

    public ResponseResult(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
