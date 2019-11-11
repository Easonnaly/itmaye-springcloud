package com.eason.shopmessage.service;

import com.eason.shopmessage.adapter.MessageAdapter;
import org.apache.commons.lang.StringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

//处理第三方发送邮件
@Slf4j
@Service
public class EmailService implements MessageAdapter {
   @Resource
   private JavaMailSender javaMailSender;

	@Override
	public void sendMsg(JSONObject body) {

		// 处理发送邮件
		String email=body.getString("email");
		log.info("准备发送邮件");
		if(StringUtils.isEmpty(email)){
			return ;
		}
		log.info("消息服务平台发送邮件:{}",email);
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		//来自账号
		simpleMailMessage.setFrom(email);
		//发送账号
		simpleMailMessage.setTo(email);
		//标题
		simpleMailMessage.setSubject("蚂蚁课堂提示您，会员成功邮件");
		//内容
		simpleMailMessage.setText("恭喜您注册成功！");
		//发送邮件
		javaMailSender.send(simpleMailMessage);
		log.info("邮件发送成功！");
	}

}
