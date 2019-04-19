package com.csy.blogServer.service;

import com.csy.blogServer.bean.User;
import com.csy.blogServer.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Map map = new HashMap();
        map.put("username",s);
        User user = userMapper.loadUserByUsername(map);
        if (user == null) {
            return new User();
        }
        return user;
    }
}
