package com.pm.demo.controller;

import com.pm.demo.entity.User;
import com.pm.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginAciton {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }
    @PostMapping("/doLogin")
    public String doLogin(String name,String pwd,ModelMap map){
        User user = userService.findByLogin(name, pwd);
        if (user!=null){
            return "redirect:/show";
        }else {
            return "login";
        }

    }
}
