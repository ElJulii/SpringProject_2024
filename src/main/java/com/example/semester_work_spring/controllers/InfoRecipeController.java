package com.example.semester_work_spring.controllers;

import com.example.semester_work_spring.dto.UserDto;
import com.example.semester_work_spring.models.Comment;
import com.example.semester_work_spring.models.Recipe;
import com.example.semester_work_spring.models.Role;
import com.example.semester_work_spring.repositories.CommentsRepository;
import com.example.semester_work_spring.repositories.RecipesRepository;
import com.example.semester_work_spring.services.CommentService;
import com.example.semester_work_spring.services.LikeService;
import com.example.semester_work_spring.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.Optional;


@Controller
public class InfoRecipeController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private RecipesRepository recipesRepository;
    @Autowired
    private LikeService likeService;
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/comments/{recipeId}")
    public String getComments(@PathVariable("recipeId") Long id, Model model,
                              @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("comments", commentService.getCommentsForRecipe(id));
        model.addAttribute("likes", likeService.getByIdRecipe(id));

        Optional<Recipe> recipeOptional = recipesRepository.findById(id);
        Optional<UserDto> userDtoOptional = usersService.findByEmail(userDetails.getUsername());


        if (userDtoOptional.isPresent() && recipeOptional.isPresent()) {
            Recipe recipeInfo =  recipeOptional.get();
            UserDto userDto = userDtoOptional.get();
            model.addAttribute("currentUsername", userDto.getFirstname());
            model.addAttribute("role", userDto.getRole());
            model.addAttribute("recipeName", recipeInfo.getNameRecipe());
            model.addAttribute("storageFileName", recipeInfo.getStorageFileName());
        }
        return "info_recipe_page";
    }

    @PostMapping("/comments/delete/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<Comment> comment = commentsRepository.findById(commentId);
        Optional<UserDto> userDtoOptional = usersService.findByEmail(userDetails.getUsername());


        if (comment.isPresent() && userDtoOptional.isPresent()) {
            UserDto userDto = userDtoOptional.get();
            Comment commentEntity = comment.get();

            if (commentEntity.getUsername().equals(userDto.getFirstname()) || userDto.getRole() == Role.ADMIN) {
                commentsRepository.deleteById(commentId);
            }
            return "redirect:/comments/" + commentEntity.getRecipe().getId();
        }

        return "redirect:/home";
    }
}
