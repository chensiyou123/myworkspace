package com.csy.blogServer.bean;

import java.util.List;

public class Menu {
    private String url;
    private List<Role> roles;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
