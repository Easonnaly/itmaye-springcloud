package com.eason.api_member.api.service;

import com.eason.api_member.entity.UserEntity;

public interface MemberService {
    //通过userId查询用户
    UserEntity findUserById(Long userId);
    //注册用户
    int addUser(UserEntity userEntity);
}
