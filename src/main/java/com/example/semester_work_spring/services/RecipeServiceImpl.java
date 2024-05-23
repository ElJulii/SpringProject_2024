package com.example.semester_work_spring.services;

import com.example.semester_work_spring.dto.RecipeDto;
import com.example.semester_work_spring.models.Recipe;
import com.example.semester_work_spring.models.User;
import com.example.semester_work_spring.repositories.RecipesRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static com.example.semester_work_spring.dto.RecipeDto.recipeList;


@Component
public class RecipeServiceImpl implements RecipeService {
    @Value("${storage.path}")
    private String storagePath;

    @Autowired
    private RecipesRepository recipesRepository;

    @Override
    public void saveRecipe(MultipartFile file, String username, String nameRecipe, String ingredients, String steps, User user) {

        String storageName = UUID.randomUUID().toString() + "." +
                FilenameUtils.getExtension(file.getOriginalFilename());

        Recipe recipe = Recipe.builder()
                .user(user)
                .username(username)
                .nameRecipe(nameRecipe)
                .ingredients(ingredients)
                .steps(steps)
                .storageFileName(storageName)
                .size(file.getSize())
                .url(storagePath + "\\" + storageName)
                .type(file.getContentType())
                .build();

        try {
            Files.copy(file.getInputStream(), Paths.get(storagePath, storageName));

        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }

        recipesRepository.save(recipe);
    }

    @Override
    public void writeFileToResponse(String recipeName, HttpServletResponse response) {
        Recipe recipe = recipesRepository.findByStorageFileName(recipeName);
        response.setContentType(recipe.getType());

        try {
            IOUtils.copy(new FileInputStream(recipe.getUrl()), response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            throw new RuntimeException("Could not read the file. Error: " + e.getMessage());
        }
    }

    @Transactional
    public void deletePost(Long postId) {
        recipesRepository.deleteById(postId);
    }

    @Override
    public List<RecipeDto> getRecipesWithUserIdOrderDesc(Long userId) {
        List<Recipe> recipesWithUserId = recipesRepository.findByUserIdOrderByIdDesc(userId);
        return recipeList(recipesWithUserId);
    }


}
