package com.cay.springsecurity01.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public String getWelcomeMsg() {
        return "Hello,Spring Security";
    }
    @GetMapping("/helloAdmin")
    @PreAuthorize("hasAnyRole('admin')")
    public String helloAdmin() {
        return "Hello,admin";
    }
    @GetMapping("/helloUser")
    @PreAuthorize("hasAnyRole('admin','normal')")
    public String helloUser() {
        return "Hello,user";
    }
}
