package com.xuyan.stock.service;

import com.xuyan.stock.pojo.entity.SysUser;
import com.xuyan.stock.vo.req.LoginReqVo;
import com.xuyan.stock.vo.resp.LoginRespVo;
import com.xuyan.stock.vo.resp.R;

public interface UserService {
    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    SysUser findByUserName(String userName);

    R<LoginRespVo> login(LoginReqVo loginReqVo);
}
