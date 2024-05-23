package com.example.semester_work_spring.services;

import com.example.semester_work_spring.dto.LikeDto;
import java.util.List;


public interface LikeService {
    void likeRecipe(Long recipeId, Long userId);
    void unlikeRecipe(Long recipeId, Long userId);
    Long countLikes(Long recipeId);

    boolean hasLikedRecipe(Long recipeId, Long userId);
    List<LikeDto> getByIdRecipe(Long id);
}
