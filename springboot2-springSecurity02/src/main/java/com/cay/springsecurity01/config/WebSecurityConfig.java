package com.cay.springsecurity01.config;

import com.cay.springsecurity01.filter.MyAccessDecisionManager;
import com.cay.springsecurity01.filter.MyFilterInvocationSecurityMetadataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
         * 配置为从内存中进行加载认证信息.
         * 这里配置了两个用户 admin和user
         */
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(passwordEncoder().encode("123456"))
                .roles("admin");
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("123456"))
                .roles("normal");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource() {
        return new MyFilterInvocationSecurityMetadataSource();
    }
    @Bean
    public MyAccessDecisionManager accessDecisionManager() {
        return new MyAccessDecisionManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()// 设置所有人都可以访问登录页面
                .antMatchers("/","/index").permitAll()
                .antMatchers("/test/**","/test1/**").permitAll()
                .antMatchers("/res/**/*.{js,html}").permitAll()
                .withObjectPostProcessor(new MyObjectPostProcessor())
                .anyRequest().authenticated()  // 任何请求,登录后可以访问
                .and()
                .formLogin().loginPage("/login")
                .and().sessionManagement().maximumSessions(1);

    }
    private class MyObjectPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {
        @Override
        public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
            fsi.setSecurityMetadataSource(filterInvocationSecurityMetadataSource());
            fsi.setAccessDecisionManager(accessDecisionManager());
            return fsi;
        }

    }

}
