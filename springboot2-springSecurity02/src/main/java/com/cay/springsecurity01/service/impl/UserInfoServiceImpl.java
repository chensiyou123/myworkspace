package com.cay.springsecurity01.service.impl;
import com.cay.springsecurity01.bean.UserInfo;
import com.cay.springsecurity01.repository.UserInfoRepository;
import com.cay.springsecurity01.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    private final static Logger logger = LoggerFactory.getLogger(UserInfoService.class);


    @Override
    public UserInfo findByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }


}
