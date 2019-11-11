package com.example.demo.controller;

import com.example.demo.doma.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/{id}")
    public String detailAction(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        return "user/detail";
    }

//    below is same
//    @GetMapping("/{user}")
//    public String detailAction(@ModelAttribute("user") User user) {
//        return "user/detail";
//    }
}
