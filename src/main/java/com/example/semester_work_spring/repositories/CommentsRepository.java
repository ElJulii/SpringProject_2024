package com.example.semester_work_spring.repositories;

import com.example.semester_work_spring.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface CommentsRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByRecipeId(Long recipeId);

}
