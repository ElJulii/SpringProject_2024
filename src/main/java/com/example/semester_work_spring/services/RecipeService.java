package com.example.semester_work_spring.services;

import com.example.semester_work_spring.dto.RecipeDto;
import com.example.semester_work_spring.models.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface RecipeService {
    void saveRecipe(MultipartFile file, String username, String nameRecipe, String ingredients, String steps, User user);
    void writeFileToResponse(String recipeName, HttpServletResponse response);
    void deletePost(Long postId);
    List<RecipeDto> getRecipesWithUserIdOrderDesc(Long userId);

}
