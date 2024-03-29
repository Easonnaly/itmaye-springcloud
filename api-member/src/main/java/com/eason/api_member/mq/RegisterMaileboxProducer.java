package com.eason.api_member.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
@Service
public class RegisterMaileboxProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMsg(Destination destination,String json){
        jmsMessagingTemplate.convertAndSend(destination,json);
    }

}
