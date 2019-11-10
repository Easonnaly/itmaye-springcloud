package com.eason.shopmessage.adapter;

import com.alibaba.fastjson.JSONObject;

//统一发送消息接口
public interface MessageAdapter {
    void sendMsg(JSONObject object);
}
