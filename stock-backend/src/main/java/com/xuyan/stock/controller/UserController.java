package com.xuyan.stock.controller;

import com.xuyan.stock.pojo.entity.SysUser;
import com.xuyan.stock.service.UserService;
import com.xuyan.stock.vo.req.LoginReqVo;
import com.xuyan.stock.vo.resp.LoginRespVo;
import com.xuyan.stock.vo.resp.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description 用户控制器
 * @createDate 2024-07-20 16:36:55
 */

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    @GetMapping("/user/{userName}")
    public SysUser getUserInfoByUserName(@PathVariable("userName") String userName) {
        return userService.findByUserName(userName);
    }

    @PostMapping("/login")
    public R<LoginRespVo> login(@RequestBody LoginReqVo loginReqVo) {
        return userService.login(loginReqVo);
    }
}
