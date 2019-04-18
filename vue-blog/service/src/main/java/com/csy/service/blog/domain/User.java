package com.csy.service.blog.domain;

import java.util.List;

public class User {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private boolean enabled;
    private List<Role> roles;
}
