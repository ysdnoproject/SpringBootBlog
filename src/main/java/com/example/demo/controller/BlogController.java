package com.example.demo.controller;

import com.example.demo.doma.entity.Blog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @GetMapping("")
    public String indexAction(Model model) {
        return "blog/index";
    }

    @GetMapping("/new")
    public String newAction(Model model) {
        return "blog/new";
    }

    @GetMapping("/{id}")
    public String detailAction(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "blog/detail";
    }

    @PostMapping("/create")
    public String createAction(@Valid Blog blog, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "blog/new";
        }

        return "redirect:/blog";
    }
}
