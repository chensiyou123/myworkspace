package com.cay.springsecurity01.service.impl;

import com.cay.springsecurity01.bean.UserInfo;
import com.cay.springsecurity01.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DataInit {
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostConstruct
    public void dataInit() {

        UserInfo admin = new UserInfo();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setRole(UserInfo.Role.admin);
        userInfoRepository.save(admin);


        UserInfo user = new UserInfo();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("123"));
        user.setRole(UserInfo.Role.normal);
        userInfoRepository.save(user);
    }

}