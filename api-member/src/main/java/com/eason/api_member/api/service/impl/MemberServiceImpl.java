package com.eason.api_member.api.service.impl;

import com.eason.api_member.api.dao.UserDao;
import com.eason.api_member.api.service.MemberService;
import com.eason.api_member.base.BaseService;
import com.eason.api_member.entity.UserEntity;
import com.eason.api_member.utils.MD5Util;
import com.sun.deploy.net.proxy.pac.PACFunctions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MemberServiceImpl extends BaseService implements MemberService{
    @Resource
    private UserDao userDao;

    @Override
    public int addUser(UserEntity userEntity) {
        String password = userEntity.getPassword();
        if (StringUtils.isEmpty(password)) {
           throw new RuntimeException("密码为空");
        }
        String newPwd = MD5Util.MD5(password);
        userEntity.setPassword(newPwd);
        return userDao.insertUser(userEntity);
    }

    @Override
    public UserEntity findUserById(Long userId) {
        return userDao.findByID(userId);
    }
}
