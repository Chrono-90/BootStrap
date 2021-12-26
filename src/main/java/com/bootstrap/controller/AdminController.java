package com.bootstrap.controller;


import com.bootstrap.model.User;
import com.bootstrap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;



    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public String createOrUpdate(@ModelAttribute("user") User user) {
        user.setPassword(encoder(user.getPassword()));
        userService.createOrUpdateUser(user);
        return "redirect:/admin/all";
    }

    @GetMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/admin/all";
    }

    @RequestMapping("/all")
    public String giveAllUsers(Model userModel, Principal principal) {
        User user = new User();
        userModel.addAttribute("loggedUser", userService.loadUserByUsername(principal.getName()));
        userModel.addAttribute("addUser", user);
        List<User> someUsers = userService.getAllUsers();
        userModel.addAttribute("listUsers", someUsers);
        return "admin/admin";
    }

    private String encoder(String codeHash) {

        if (codeHash.length() < 60)  {
            codeHash = passwordEncoder.encode(codeHash);
        }
        return codeHash;

    }
}