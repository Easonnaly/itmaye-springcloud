package com.eason.api_member.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.eason.api_member.api.dao.UserDao;
import com.eason.api_member.api.service.MemberService;
import com.eason.api_member.base.BaseService;
import com.eason.api_member.base.ResponseResult;
import com.eason.api_member.constants.Constants;
import com.eason.api_member.entity.UserEntity;
import com.eason.api_member.utils.MD5Util;
import com.eason.api_member.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@Slf4j
@RestController
@RequestMapping("member")
public class MemberController extends BaseService {
    @Resource
    private MemberService memberService;
    @Resource
    private UserDao userDao;

    /**
     * 描述信息
     * 查询用户
     *
     * @param userId
     * @return com.eason.api_member.base.ResponseResult<com.eason.api_member.entity.UserEntity>
     * @author eason
     * @date 10:32 2019/11/7
     **/
    @RequestMapping("findById")
    public ResponseResult getMember(Long userId) {
        UserEntity user = memberService.findUserById(userId);
        if (user == null) {
            return setResultError("no message");
        }
        return setResultSuccess(user);
    }

    @RequestMapping("register")
    public ResponseResult registerUser(@RequestBody UserEntity userEntity) {
        int i = memberService.addUser(userEntity);
        if (i > 0) {
            return setResultSuccess("注册成功");
        } else {
            return setResultError("注册失败");
        }
    }

    @RequestMapping("login")
    public ResponseResult login(@RequestBody UserEntity userEntity){
        //1.验证参数
        String username = userEntity.getUsername();
        if (StringUtils.isEmpty(username)) {
            return setResultError("用户名不能为空");
        }
        String password = userEntity.getPassword();
        if (StringUtils.isEmpty(password)) {
            return setResultError("密码不能为空");
        }
        //2.数据库查找账号密码是否正确
        String newPwd = MD5Util.MD5(password);
        UserEntity login = userDao.login(username, newPwd);
        if(login==null){
            return setResultError("账号密码不正确");
        }
        //3.如果账号密码正确,生成token
        String memberToken = TokenUtils.getMemberToken();
        //4.存在redis中，key为token，value为userId
        Integer userId = login.getId();
        log.info("###用户信息token存放在redis中，key为:{},value为:{}",memberToken,userId);
        baseRedisService.setString(memberToken,userId+"", Constants.TOKEN_MEMBER_TIME);
        //5.返回token
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("memberToken",memberToken);
        return setResultSuccess(jsonObject);
    }

    @RequestMapping("findByTokenUser")
    public ResponseResult findByTokenUser(String token){
        //1.验证参数
        if (StringUtils.isEmpty(token)){
            return setResultError("token不能为空");
        }
        //2.从redis查询userId
        String strUserId = baseRedisService.getString(token);
        if (StringUtils.isEmpty(strUserId)){
            return setResultError("token无效或者已过期");
        }
        //3.查询数据库
        long userId = Long.parseLong(strUserId);
        UserEntity userDaoByID = userDao.findByID(userId);
        if (userDaoByID==null) {
            return setResultError("未查到用户信息");
        }
        return setResultSuccess(userDaoByID);
    }
}
