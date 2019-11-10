package com.eason.shopmessage.service;

import com.eason.shopmessage.adapter.MessageAdapter;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

//处理第三方发送邮件
@Slf4j
@Service
public class EmailService implements MessageAdapter {
   
	@Override
	public void sendMsg(JSONObject body) {
		// 处理发送邮件
		String email=body.getString("email");
		log.info("准备发送邮件");
		if(StringUtils.isEmpty(email)){
			return ;
		}
		log.info("消息服务平台发送邮件:{}",email);
	}

}
