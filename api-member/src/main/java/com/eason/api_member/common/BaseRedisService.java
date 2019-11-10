package com.eason.api_member.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2019-2025, xingyun information technology wuxi Co., Ltd.
 * redis工具类
 * @author Eason
 * @version 1.00
 * @date 2019/11/5 22:33
 */
@Component
public class BaseRedisService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void setString(String key, Object data) {
        setString(key, data, null);
    }

    public void setString(String key, Object data, Long timeout) {
        if (data instanceof String) {
            String value = (String) data;
            stringRedisTemplate.opsForValue().set(key, value);
        }
        if (timeout != null) {
            stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
    }

    public String getString(String key) {
        return (String) stringRedisTemplate.opsForValue().get(key);
    }

    public void delKey(String key) {
        stringRedisTemplate.delete(key);
    }
}
