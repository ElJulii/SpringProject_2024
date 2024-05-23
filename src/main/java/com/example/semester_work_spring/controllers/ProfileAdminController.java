package com.example.semester_work_spring.controllers;

import com.example.semester_work_spring.dto.UserDto;
import com.example.semester_work_spring.repositories.RecipesRepository;
import com.example.semester_work_spring.services.RecipeService;
import com.example.semester_work_spring.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ProfileAdminController {
    @Autowired
    private RecipesRepository recipesRepository;
    @Autowired
    private UsersService usersService;

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/profile/admin")
    public String getProfileAdmin(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        Optional<UserDto> userDtoOptional = usersService.findByEmail(userDetails.getUsername());

        if (userDtoOptional.isPresent()) {
            UserDto userDto = userDtoOptional.get();
            String userName = userDto.getFirstname();
            model.addAttribute("username", userName);
        }

        model.addAttribute("recipesList", recipesRepository.findAll());
        return "profile_admin_page";
    }


    @GetMapping("profile/admin/delete/{postId}")
    public String deletePost(@PathVariable("postId") Long postId) {
        recipeService.deletePost(postId);
        return "redirect:/profile/admin";
    }
}
