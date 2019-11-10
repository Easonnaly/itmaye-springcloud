package com.eason.api_member.api.controller;

import com.eason.api_member.api.service.ApiService;
import com.eason.api_member.base.BaseService;
import com.eason.api_member.base.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Copyright (C), 2019-2025, xingyun information technology wuxi Co., Ltd.
 *
 * @author Eason
 * @version 1.00
 * @date 2019/11/5 21:47
 */
@RestController
@RequestMapping("api")
public class ApiController extends BaseService{
    @Resource
    private ApiService apiService;


    @RequestMapping("test")
    public Map<String,Object> test(Integer id,String name){
        Map<String, Object> test = apiService.test(id, name);
        return test;
    }

    @RequestMapping("redis")
    public ResponseResult testRedis(String key,String value){
        baseRedisService.setString(key,value,null);
        responseResult.setCode(200);
        responseResult.setMessage("success");
        return responseResult;
    }

}
