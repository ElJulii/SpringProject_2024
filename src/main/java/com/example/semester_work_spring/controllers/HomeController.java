package com.example.semester_work_spring.controllers;

import com.example.semester_work_spring.dto.CommentForm;
import com.example.semester_work_spring.dto.RecipeDto;
import com.example.semester_work_spring.dto.UserDto;
import com.example.semester_work_spring.models.Recipe;
import com.example.semester_work_spring.models.User;
import com.example.semester_work_spring.repositories.RecipesRepository;
import com.example.semester_work_spring.services.CommentService;
import com.example.semester_work_spring.services.LikeService;
import com.example.semester_work_spring.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class HomeController {

    @Autowired
    private RecipesRepository recipesRepository;
    @Autowired
    private LikeService likeService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UsersService usersService;


    @GetMapping("/home")
    public String getHomePage(Model model,
                              @AuthenticationPrincipal UserDetails userDetails,
                              @RequestParam(defaultValue = "1") int page,
                              @RequestParam(name = "search", required = false) String search ) {
        Optional<UserDto> userDtoOptional = usersService.findByEmail(userDetails.getUsername());
        if (userDtoOptional.isPresent()) {
            UserDto userDto = userDtoOptional.get();
            model.addAttribute("role", userDto.getRole());
            model.addAttribute("userId", userDto.getId());
        }
        Pageable pageable = PageRequest.of(page - 1, 4);
        Page<Recipe> recipesPage;

        if (search != null && !search.isEmpty()) {
            recipesPage = recipesRepository.findByNameRecipeContainingIgnoreCase(search, pageable);
        } else {
            recipesPage = recipesRepository.findAllByOrderByIdDesc(pageable);
        }

        List<RecipeDto> recipeDtos = recipesPage.getContent().stream().map(recipe -> {
            RecipeDto recipeDto = RecipeDto.from(recipe);
            recipeDto.setLikeCount(likeService.countLikes(recipe.getId()));
            return recipeDto;
        }).collect(Collectors.toList());



        model.addAttribute("recipes", recipeDtos);
        model.addAttribute("totalPages", recipesPage.getTotalPages());
        model.addAttribute("currentPage", page);

        return "home_page";
    }

    @PostMapping("/comment")
    public String addComment(CommentForm commentForm,
                             @RequestParam("recipeId") Recipe recipeId,
                             @AuthenticationPrincipal UserDetails userDetails) {
        Optional<UserDto> userDtoOptional = usersService.findByEmail(userDetails.getUsername());

        if (userDtoOptional.isPresent()) {
            UserDto userDto = userDtoOptional.get();
            String userName = userDto.getFirstname();
            User user = User.builder()
                    .id(userDto.getId()).build();
            commentService.addComment(commentForm, userName, user, recipeId);
        }
        return "redirect:/home";
    }

    @PostMapping("/like")
    public String likeRecipe(@RequestParam Long recipeId,
                             @AuthenticationPrincipal UserDetails userDetails) {
        Optional<UserDto> userDtoOptional = usersService.findByEmail(userDetails.getUsername());
        if (userDtoOptional.isPresent()) {
            UserDto userDto = userDtoOptional.get();
            Long userId = userDto.getId();

            if (!likeService.hasLikedRecipe(recipeId, userId)) {
                likeService.likeRecipe(recipeId, userDto.getId());
            }
        }
        return "redirect:/home";
    }

    @PostMapping("/unlike")
    public String unlikeRecipe(@RequestParam Long recipeId,
                               @AuthenticationPrincipal UserDetails userDetails) {
        Optional<UserDto> userDtoOptional = usersService.findByEmail(userDetails.getUsername());
        if (userDtoOptional.isPresent()) {
            UserDto userDto = userDtoOptional.get();
            likeService.unlikeRecipe(recipeId, userDto.getId());
        }
        return "redirect:/home";
    }
}
