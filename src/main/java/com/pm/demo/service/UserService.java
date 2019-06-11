package com.pm.demo.service;

import com.pm.demo.entity.User;

public interface UserService {
    User findByLogin(String uname, String pwd);
}
