package com.xuyan.stock.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.xuyan.stock.constant.StockConstant;
import com.xuyan.stock.mapper.SysUserMapper;
import com.xuyan.stock.pojo.entity.SysUser;
import com.xuyan.stock.service.UserService;
import com.xuyan.stock.utils.IdWorker;
import com.xuyan.stock.vo.req.LoginReqVo;
import com.xuyan.stock.vo.resp.LoginRespVo;
import com.xuyan.stock.vo.resp.R;
import com.xuyan.stock.vo.resp.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public SysUser findByUserName(String userName) {
        return sysUserMapper.findUserInfoByUserName(userName);
    }

    @Override
    public R<LoginRespVo> login(LoginReqVo loginReqVo) {
        if(loginReqVo == null || StringUtils.isBlank(loginReqVo.getUsername()) || StringUtils.isBlank(loginReqVo.getPassword())){
            return R.error(ResponseCode.DATA_ERROR);
        }
        //判断验证码是否存在
//        if(StringUtils.isBlank(loginReqVo.getCode()) || StringUtils.isBlank(loginReqVo.getSessionId())){
//            return R.error(ResponseCode.DATA_ERROR);
//        }
//        //判断redis保存的验证码和输入的是否一致，忽略大小写
//        String redisCode = (String) redisTemplate.opsForValue().get(StockConstant.CHECK_PREFIX + loginReqVo.getSessionId());
//        if(StringUtils.isBlank(redisCode)){
//            return R.error(ResponseCode.CHECK_CODE_EXPIRED);
//        }
//        if(!redisCode.equalsIgnoreCase(loginReqVo.getCode())){
//            return R.error(ResponseCode.CHECK_CODE_ERROR);
//        }
        String username = loginReqVo.getUsername();
        String password = loginReqVo.getPassword();

        SysUser dbUser = sysUserMapper.findUserInfoByUserName(username);
        if(dbUser == null){
            return R.error(ResponseCode.ACCOUNT_NOT_EXISTS);
        }
        if(!passwordEncoder.matches(password, dbUser.getPassword())){
            return R.error(ResponseCode.USERNAME_OR_PASSWORD_ERROR);
        }
        LoginRespVo loginRespVo = new LoginRespVo();
        BeanUtils.copyProperties(dbUser, loginRespVo);

        return R.ok(loginRespVo);
    }

    @Override
    public R<Map> getCaptcha() {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(250, 40, 4, 5);
        String checkCode = captcha.getCode();
        String imgData = captcha.getImageBase64();
        String sessionID = String.valueOf(idWorker.nextId());
        log.info("当前生成的图片校验码{}，会话id{}",checkCode, sessionID);
        redisTemplate.opsForValue().set(StockConstant.CHECK_PREFIX+sessionID, checkCode, 1, TimeUnit.MINUTES);
        Map<String, String> map = new HashMap<>();
        map.put("image", imgData);
        map.put("sessionID", sessionID);
        return R.ok(map);
    }
}
