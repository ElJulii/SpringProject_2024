package com.example.semester_work_spring.controllers;

import com.example.semester_work_spring.dto.UserDto;
import com.example.semester_work_spring.models.Recipe;
import com.example.semester_work_spring.repositories.RecipesRepository;
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
public class EditRecipeController {
    @Autowired
    private RecipesRepository recipesRepository;
    @Autowired
    private UsersService usersService;

    @GetMapping("/recipes/edit")
    public String getEditPage(@RequestParam("id") Long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<UserDto> userDtoOptional = usersService.findByEmail(userDetails.getUsername());
        if (userDtoOptional.isPresent()) {
            UserDto userDto = userDtoOptional.get();
            Optional<Recipe> recipeOptional = recipesRepository.findById(id);
            if (recipeOptional.isPresent() && recipeOptional.get().getUser().getId().equals(userDto.getId())) {
                model.addAttribute("recipe", recipeOptional.get());
            }
        }
        return "edit_post_page";
    }

    @PostMapping("/recipes/edit")
    public String editRecipe(@RequestParam("id") Long id,
                             @RequestParam(value = "nameRecipe", required = false) String nameRecipe,
                             @RequestParam(value = "ingredients", required = false) String ingredients,
                             @RequestParam(value = "steps", required = false) String steps,
                             @AuthenticationPrincipal UserDetails userDetails) {
        Optional<UserDto> userDtoOptional = usersService.findByEmail(userDetails.getUsername());
        if (userDtoOptional.isPresent()) {
            UserDto userDto = userDtoOptional.get();
            Optional<Recipe> recipeOptional = recipesRepository.findById(id);
            if (recipeOptional.isPresent() && recipeOptional.get().getUser().getId().equals(userDto.getId())) {
                Recipe recipe = recipeOptional.get();
                if (nameRecipe != null && !nameRecipe.isEmpty()) {
                    recipe.setNameRecipe(nameRecipe);
                }
                if (ingredients != null && !ingredients.isEmpty()) {
                    recipe.setIngredients(ingredients);
                }
                if (steps != null && !steps.isEmpty()) {
                    recipe.setSteps(steps);
                }
                recipesRepository.save(recipe);
            }
        }
        return "redirect:/profile/user";
    }
}
