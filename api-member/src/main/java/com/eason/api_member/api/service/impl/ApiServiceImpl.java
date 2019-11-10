package com.eason.api_member.api.service.impl;

import com.eason.api_member.api.service.ApiService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2019-2025, xingyun information technology wuxi Co., Ltd.
 *
 * @author Eason
 * @version 1.00
 * @date 2019/11/5 21:48
 */
@Service
public class ApiServiceImpl implements ApiService{
    @Override
    public Map<String, Object> test(Integer id, String name) {
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","success");
        result.put("data",new String[]{"111","222",""+id,name});
        return result;
    }
}
