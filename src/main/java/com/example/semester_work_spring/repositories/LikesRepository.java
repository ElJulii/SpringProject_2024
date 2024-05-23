package com.example.semester_work_spring.repositories;

import com.example.semester_work_spring.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Like, Long> {
    boolean existsByUserIdAndRecipeId(Long userId, Long recipeId);
    Long countByRecipeId(Long recipeId);
    void deleteByUserIdAndRecipeId(Long userId, Long recipeId);
    List<Like> findByRecipeIdOrderByIdDesc(Long recipeId);
}
