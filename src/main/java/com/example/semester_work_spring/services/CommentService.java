package com.example.semester_work_spring.services;

import com.example.semester_work_spring.dto.CommentDto;
import com.example.semester_work_spring.dto.CommentForm;
import com.example.semester_work_spring.models.Recipe;
import com.example.semester_work_spring.models.User;
import java.util.List;

public interface CommentService {
    void addComment(CommentForm commentForm, String username, User user, Recipe recipe);
    List<CommentDto> getCommentsForRecipe(Long recipeId);
}
