package com.xuyan.stock.service.impl;

import com.xuyan.stock.mapper.SysUserMapper;
import com.xuyan.stock.pojo.entity.SysUser;
import com.xuyan.stock.service.UserService;
import com.xuyan.stock.vo.req.LoginReqVo;
import com.xuyan.stock.vo.resp.LoginRespVo;
import com.xuyan.stock.vo.resp.R;
import com.xuyan.stock.vo.resp.ResponseCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public SysUser findByUserName(String userName) {
        return sysUserMapper.findUserInfoByUserName(userName);
    }

    @Override
    public R<LoginRespVo> login(LoginReqVo loginReqVo) {
        if(loginReqVo == null || StringUtils.isBlank(loginReqVo.getUsername()) || StringUtils.isBlank(loginReqVo.getPassword())){
            return R.error(ResponseCode.DATA_ERROR);
        }
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
}
