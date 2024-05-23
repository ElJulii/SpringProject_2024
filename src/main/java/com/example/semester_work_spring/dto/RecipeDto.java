package com.example.semester_work_spring.dto;

import com.example.semester_work_spring.models.Recipe;
import com.example.semester_work_spring.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {
    private Long id;
    private User user;
    private String username;
    private String nameRecipe;
    private String ingredients;
    private String steps;

    private String storageFileName;
    private Long likeCount;

    public static RecipeDto from (Recipe recipe) {
       return RecipeDto.builder()
               .id(recipe.getId())
               .nameRecipe(recipe.getNameRecipe())
               .user(recipe.getUser())
               .username(recipe.getUsername())
               .ingredients(recipe.getIngredients())
               .steps(recipe.getSteps())
               .storageFileName(recipe.getStorageFileName())
               .build();
    }

    public static List<RecipeDto> recipeList(List<Recipe> recipes) {
        return recipes.stream()
                .map(RecipeDto::from)
                .collect(Collectors.toList());
    }
}
