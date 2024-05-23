package com.example.semester_work_spring.repositories;

import com.example.semester_work_spring.models.Recipe;;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipesRepository extends JpaRepository<Recipe, Long> {
    Recipe findByStorageFileName(String fileName);
    List<Recipe> findByUserIdOrderByIdDesc(Long id);
    Page<Recipe> findAllByOrderByIdDesc(Pageable pageable);
    Page<Recipe> findByNameRecipeContainingIgnoreCase(String search, Pageable pageable);

}
