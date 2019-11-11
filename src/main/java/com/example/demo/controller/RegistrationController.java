package com.example.demo.controller;

import com.example.demo.doma.entity.User;
import com.example.demo.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class RegistrationController {
    @NonNull
    private UserService userService;

    @GetMapping("/signup")
    public String newAction(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String createAction(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirAttrs) {
        if (userService.findByName(user.getName()).isPresent()) {
            bindingResult
                    .rejectValue("name", "error.name",
                            "There is already a user registered with the username provided");
        }

        if (!bindingResult.hasErrors()) {
            // Registration successful, save user
            // Set user role to USER and set it as active
            userService.insert(user);

            redirAttrs.addFlashAttribute("message", "User has been registered successfully");

            return "redirect:/login";
        }

        return "signup";
    }
}
