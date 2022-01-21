package com.group2.kelem.controller;

import com.group2.kelem.dao.UserRepository;
import com.group2.kelem.model.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepo;
    
    /**
     * This method returns the custom login page we designed.
     * @return
     */
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "login";
    }
}
