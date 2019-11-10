package com.eason.api_member.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.eason.api_member.api.dao.UserDao;
import com.eason.api_member.api.service.MemberService;
import com.eason.api_member.base.BaseService;
import com.eason.api_member.entity.UserEntity;
import com.eason.api_member.mq.RegisterMaileboxProducer;
import com.eason.api_member.utils.MD5Util;
import com.sun.deploy.net.proxy.pac.PACFunctions;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Slf4j
@Service
public class MemberServiceImpl extends BaseService implements MemberService {
    @Resource
    private UserDao userDao;


    @Resource
    private RegisterMaileboxProducer producer;
    @Value("${messages.queue}")
    private String MessageQueue;
    @Override
    public int addUser(UserEntity userEntity) {
        String password = userEntity.getPassword();
        if (StringUtils.isEmpty(password)) {
            throw new RuntimeException("密码为空");
        }
        String newPwd = MD5Util.MD5(password);
        userEntity.setPassword(newPwd);
        //异步方式发送消息
        String email = userEntity.getEmail();
        String json = emilJson(email);
        log.info("会员服务推送消息到消息服务平台****json:{}",json);
        sendMsg(json);
        return userDao.insertUser(userEntity);
    }

    private String emilJson(String email){
        JSONObject rootJson = new JSONObject();
        JSONObject header = new JSONObject();
        header.put("interfaceType","email");
        JSONObject content = new JSONObject();
        content.put("email",email);
        rootJson.put("header",header);
        rootJson.put("content",content);
        log.info("会员服务推送消息到消息服务平台****结束****:{}");

        return rootJson.toJSONString();
    }

    private void sendMsg(String json){
        ActiveMQQueue activeMQQueue = new ActiveMQQueue(MessageQueue);
        producer.sendMsg(activeMQQueue,json);
    }

    @Override
    public UserEntity findUserById(Long userId) {
        return userDao.findByID(userId);
    }
}
