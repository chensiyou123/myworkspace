package com.cay.springsecurity01.filter;

import com.cay.springsecurity01.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionService permissionService;
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        System.out.println("MyFilterInvocationSecurityMetadataSource.getAttributes()");
        java.util.Map<String, Collection<ConfigAttribute>> map = permissionService.getPermissionMap();
        FilterInvocation filterInvocation = (FilterInvocation) object;
        System.out.println(filterInvocation.getFullRequestUrl());
        if (isMatcherAllowedRequest(filterInvocation)) return null ; //return null 表示允许访问，不做拦截
        HttpServletRequest request = filterInvocation.getHttpRequest();
        String resUrl; //URL规则匹配.
        AntPathRequestMatcher matcher;
        for(Iterator<String> it  = map.keySet().iterator();it.hasNext();) {
            resUrl = it.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                System.out.println(map.get(resUrl));
                return map.get(resUrl);
            }
        }
        //SecurityConfig.createList("ROLE_USER");
        //方式一：没有匹配到,直接是白名单了.不登录也是可以访问的。
        //return null;
        //方式二：配有匹配到，需要指定相应的角色：
        return SecurityConfig.createList("ROLE_admin");
    }
    /**
     * 判断当前请求是否在允许请求的范围内
     * @param fi 当前请求
     * @return 是否在范围中
     */
    private boolean isMatcherAllowedRequest(FilterInvocation fi){
        return allowedRequest().stream().map(AntPathRequestMatcher::new).filter(requestMatcher -> requestMatcher.matches(fi.getHttpRequest())).toArray().length > 0;
    }
    /**
     * @return 定义允许请求的列表
     */
    private List<String> allowedRequest(){
        return Arrays.asList("/login","/css/**","/fonts/**","/js/**","/scss/**","/img/**");
    }
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
