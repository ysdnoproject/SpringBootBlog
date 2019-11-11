package com.example.demo.controller;

import com.example.demo.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {
    @NonNull
    private UserService userService;

    @GetMapping("/")
    public String indexAction(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }
}
