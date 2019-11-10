package com.eason.api_member.api.controller;

import com.eason.api_member.api.service.MemberService;
import com.eason.api_member.base.BaseService;
import com.eason.api_member.base.ResponseResult;
import com.eason.api_member.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("member")
public class MemberController extends BaseService {
    @Resource
    private MemberService memberService;

    /**
     * 描述信息
        查询用户
     * @param userId
     * @return com.eason.api_member.base.ResponseResult<com.eason.api_member.entity.UserEntity>
     * @author eason
     * @date 10:32 2019/11/7
     **/
    @RequestMapping("findById")
    public ResponseResult getMember(Long userId){
        UserEntity user = memberService.findUserById(userId);
        if (user==null){
           return setResultError("no message");
        }
        return setResultSuccess(user);
    }

    @RequestMapping("register")
    public ResponseResult registerUser(@RequestBody UserEntity userEntity){
        int i = memberService.addUser(userEntity);
        if (i>0){
            return setResultSuccess("注册成功");
        }else {
            return setResultError("注册失败");
        }
    }

}
