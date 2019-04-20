package com.cay.springsecurity01.service.impl;

import com.cay.springsecurity01.bean.Permission;
import com.cay.springsecurity01.bean.Role;
import com.cay.springsecurity01.repository.PermissionRepository;
import com.cay.springsecurity01.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;


@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    private Map<String, Collection<ConfigAttribute>> permissionMap = null;

    @PostConstruct
    public void initPermissions() {
        System.out.println("PermissionServiceImpl.initPermissions()");
        permissionMap = new HashMap<>();
        Collection<ConfigAttribute> collection;
        ConfigAttribute cfg;
        List<Permission> permissions = permissionRepository.findAll();
        for(Permission p:permissions) {
            collection = new ArrayList<ConfigAttribute>();
            for(Role r:p.getRoles()) {
                cfg = new SecurityConfig("ROLE_"+r.getName());
                collection.add(cfg);
            }
            permissionMap.put(p.getUrl(),collection);
        }
        System.out.println(permissionMap);
    }

    @Override
    public Map<String, Collection<ConfigAttribute>> getPermissionMap() {
        if(permissionMap.size()==0) initPermissions();
        return permissionMap;
    }
}
