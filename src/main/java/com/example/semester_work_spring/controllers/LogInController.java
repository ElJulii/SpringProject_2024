package com.example.semester_work_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInController {

    @GetMapping("/logIn")
    public String getLogInPage() {
        return "log_in_page";
    }
}
