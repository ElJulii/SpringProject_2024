package com.example.semester_work_spring.controllers;

import com.example.semester_work_spring.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ConfirmController {
    @Autowired
    private SignUpService signUpService;

    @GetMapping("/confirm/{code}")
    public String confirmUser (@PathVariable("code") String code, Model model) {
        model.addAttribute("confirm_code", code);
        signUpService.confirmUser(code);
        return "confirm_message";
    }
}
