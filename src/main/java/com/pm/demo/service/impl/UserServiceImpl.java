package com.pm.demo.service.impl;

import com.pm.demo.entity.User;
import com.pm.demo.mapper.UserMapper;
import com.pm.demo.service.UserService;
import com.pm.demo.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public User findByLogin(String uname, String pwd) {
        User user = userMapper.findByLogin(uname, pwd);
        if(user!=null){
            stringRedisTemplate.opsForValue().set("user", JsonUtils.objectToJson(user));
        }
        return user;
    }
}
