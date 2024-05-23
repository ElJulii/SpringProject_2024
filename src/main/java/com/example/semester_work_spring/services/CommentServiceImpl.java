package com.example.semester_work_spring.services;

import com.example.semester_work_spring.dto.CommentDto;
import com.example.semester_work_spring.dto.CommentForm;
import com.example.semester_work_spring.models.Comment;
import com.example.semester_work_spring.models.Recipe;
import com.example.semester_work_spring.models.User;
import com.example.semester_work_spring.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.semester_work_spring.dto.CommentDto.commentList;


@Component
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentsRepository commentsRepository;
    @Override
    public void addComment(CommentForm commentForm, String username, User user, Recipe recipe) {
        Comment comment = Comment.builder()
                .comment(commentForm.getComment())
                .username(username)
                .user(user)
                .recipe(recipe)
                .build();
        commentsRepository.save(comment);
    }
    @Override
    public List<CommentDto> getCommentsForRecipe(Long recipeId) {
        List<Comment> commentsForRecipe = commentsRepository.findByRecipeId(recipeId);
        return commentList(commentsForRecipe);
    }


}
