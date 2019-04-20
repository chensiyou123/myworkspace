package com.cay.springsecurity01.controller;

import com.cay.springsecurity01.bean.UserInfo;
import com.cay.springsecurity01.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private UserInfoService userInfoService;
    @GetMapping
    public String getWelcomeMsg() {
        return "Hello,Spring Security";
    }
    @GetMapping("/helloAdmin")
    // @PreAuthorize("hasAnyRole('admin')")
    public String helloAdmin() {
        return "Hello,admin";
    }
    @GetMapping("/helloUser")
    //@PreAuthorize("hasAnyRole('admin','normal')")
    public String helloUser() {
        return "Hello,user";
    }
    @GetMapping("/user")
    public UserInfo user() {
        return userInfoService.findByUsername("");
    }

    @GetMapping("/helloAdmin/admin")
    // @PreAuthorize("hasAnyRole('admin')")
    public String admin() {
        return "admin";
    }
}
