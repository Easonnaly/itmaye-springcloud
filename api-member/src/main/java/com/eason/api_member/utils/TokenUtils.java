package com.eason.api_member.utils;

import java.util.UUID;

public class TokenUtils {
    public static String getMemberToken(){
        return "token-"+ UUID.randomUUID();
    }
}
