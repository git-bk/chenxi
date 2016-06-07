package com.bk.chenxi.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class Test {

    String      name = "Vincent";
    UserDetails userInfo;

    public String getName() {
        return "3eer";
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDetails getUserInfo() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
