package com.cay.springsecurity01.service;

import com.cay.springsecurity01.bean.UserInfo;

public interface UserInfoService{
    UserInfo findByUsername(String username);
}
