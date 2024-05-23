package com.example.semester_work_spring.controllers;

import com.example.semester_work_spring.dto.RecipeDto;
import com.example.semester_work_spring.dto.UserDto;
import com.example.semester_work_spring.models.Role;
import com.example.semester_work_spring.models.User;
import com.example.semester_work_spring.repositories.RecipesRepository;
import com.example.semester_work_spring.services.RecipeService;
import com.example.semester_work_spring.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileUserController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UsersService usersService;

    @GetMapping("/profile/user")
    public String getProfileUser(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Optional<UserDto> userDtoOptional = usersService.findByEmail(userDetails.getUsername());
        if (userDtoOptional.isPresent()) {
            UserDto userDto = userDtoOptional.get();
            String userName = userDto.getFirstname();

            Long userId = userDto.getId();

            List<RecipeDto> userRecipe = recipeService.getRecipesWithUserIdOrderDesc(userId);

            model.addAttribute("username", userName);
            model.addAttribute("recipes", userRecipe);
        }

        return "profile_user_page";
    }

    @PostMapping("/profile/user")
    public ResponseEntity<String> recipeUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("nameRecipe") String nameRecipe,
            @RequestParam("ingredients") String ingredients,
            @RequestParam("steps") String steps,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Optional<UserDto> userDtoOptional = usersService.findByEmail(userDetails.getUsername());
        if (userDtoOptional.isPresent()) {
            UserDto userDto = userDtoOptional.get();
            String userName = userDto.getFirstname();
            User user = User.builder()
                    .id(userDto.getId())
                    .build();

            recipeService.saveRecipe(file, userName, nameRecipe, ingredients, steps, user);
            return ResponseEntity.ok().body("/profile/user");
        } else {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @GetMapping("/profile/user/{recipe-name:.+}")
    public void getFile(@PathVariable("recipe-name") String recipeName, HttpServletResponse response) {
        recipeService.writeFileToResponse(recipeName, response);
    }

    @GetMapping("profile/user/delete/{postId}")
    public String deletePost(@PathVariable("postId") Long postId) {
        recipeService.deletePost(postId);
        return "redirect:/profile/user";
    }
}
