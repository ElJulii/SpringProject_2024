package com.example.semester_work_spring.controllers;

import com.example.semester_work_spring.dto.UserDto;
import com.example.semester_work_spring.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserEditController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/user/edit")
    public String getEditUserPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<UserDto> userDtoOptional = usersService.findByEmail(userDetails.getUsername());
        userDtoOptional.ifPresent(userDto -> model.addAttribute("user", userDto));
        return "edit_user_page";
    }

    @PostMapping("/user/edit")
    public String editUser(@RequestParam(value = "firstname", required = false) String firstname,
                           @RequestParam(value = "lastname", required = false) String lastname,
                           @AuthenticationPrincipal UserDetails userDetails) {
        Optional<UserDto> userDtoOptional = usersService.findByEmail(userDetails.getUsername());
        if (userDtoOptional.isPresent()) {
            UserDto userDto = userDtoOptional.get();
            if (firstname != null && !firstname.isEmpty()) {
                userDto.setFirstname(firstname);
            }
            if (lastname != null && !lastname.isEmpty()) {
                userDto.setLastname(lastname);
            }
            usersService.updateUser(userDto);;
        }
        return "redirect:/profile/user";
    }
}
