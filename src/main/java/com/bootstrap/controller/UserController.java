package com.bootstrap.controller;


import com.bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user")
    public String userByName(Model userModel) {
        userModel.addAttribute("user", userService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "user/user";
    }
    @GetMapping("/access_denied")
    public String acces() {

        return "user/access_denied";
    }
}