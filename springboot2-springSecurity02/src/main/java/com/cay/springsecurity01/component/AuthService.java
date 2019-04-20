package com.cay.springsecurity01.component;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthService {
    public boolean canAccess(HttpServletRequest request, Authentication authentication) {
        //在这里编写校验代码…
        return true;
    }
}
