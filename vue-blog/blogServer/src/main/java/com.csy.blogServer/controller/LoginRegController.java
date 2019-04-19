package com.csy.blogServer.controller;

import com.csy.blogServer.service.UserService;
import com.csy.blogServer.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRegController {
    @Autowired
    UserService userService;
    @RequestMapping("/login")
    public RespBean login(String username) {
        userService.loadUserByUsername(username);
        return new RespBean("error", "登录失败!");
    }
}
