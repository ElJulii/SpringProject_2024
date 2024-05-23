package com.example.semester_work_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String getWelcomePage() {
        return "welcome_page";
    }
}
