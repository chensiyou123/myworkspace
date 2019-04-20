package com.csy.blogServer.service;

import com.csy.blogServer.bean.Role;
import com.csy.blogServer.bean.User;
import com.csy.blogServer.mapper.RolesMapper;
import com.csy.blogServer.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RolesMapper rolesMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Map map = new HashMap();
        map.put("username",s);
        User user = userMapper.loadUserByUsername(map);
        if (user == null) {
            return new User();
        }
        //查询用户的角色信息，并返回存入user中
        List<Role> roles = rolesMapper.getRolesByUid(user.getId());
        user.setRoles(roles);
        return user;
    }
}
