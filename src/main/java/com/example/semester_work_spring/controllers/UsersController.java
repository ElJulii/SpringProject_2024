package com.example.semester_work_spring.controllers;

import com.example.semester_work_spring.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("usersList", usersService.getAllUsers());
        return "users_page";
    }

    @GetMapping("/users/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long userID) {
        usersService.deleteUser(userID);
        return "redirect:/users";
    }
}
