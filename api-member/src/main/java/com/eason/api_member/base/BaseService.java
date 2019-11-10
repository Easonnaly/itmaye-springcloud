package com.eason.api_member.base;

import com.eason.api_member.common.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;


/**
 * Copyright (C), 2019-2025, xingyun information technology wuxi Co., Ltd.
 *
 * @author Eason
 * @version 1.00
 * @date 2019/11/5 22:43
 */
public class BaseService<T> {

   protected ResponseResult<T> responseResult;

    @Autowired
   protected BaseRedisService baseRedisService;

    @ModelAttribute
    public void setResAnReq(){
        this.responseResult = new ResponseResult<T>();
    }

    protected ResponseResult setResultSuccess(T t){
        responseResult.setCode(200);
        responseResult.setMessage("success");
        responseResult.setData(t);
        return responseResult;
    }
    protected ResponseResult setResultSuccess(){
        responseResult.setCode(200);
        responseResult.setMessage("success");
        return responseResult;
    }

    protected ResponseResult setResultSuccess(String message){
        responseResult.setCode(200);
        responseResult.setMessage(message);
        return responseResult;
    }

    protected ResponseResult setResultError(String message){
        responseResult.setCode(400);
        responseResult.setMessage(message);
        return responseResult;
    }
}
