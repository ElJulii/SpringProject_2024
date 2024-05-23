package com.example.semester_work_spring.services;


import com.example.semester_work_spring.dto.LikeDto;
import com.example.semester_work_spring.models.Like;
import com.example.semester_work_spring.models.Recipe;
import com.example.semester_work_spring.models.User;
import com.example.semester_work_spring.repositories.LikesRepository;
import com.example.semester_work_spring.repositories.RecipesRepository;
import com.example.semester_work_spring.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.example.semester_work_spring.dto.LikeDto.likeList;

@Component
public class LikeServiceImpl implements LikeService {
    @Autowired
    private RecipesRepository recipesRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private LikesRepository likesRepository;
    @Override
    public void likeRecipe(Long recipeId, Long userId) {
        Recipe recipe = recipesRepository.findById(recipeId).orElseThrow();
        User user = usersRepository.findById(userId).orElseThrow();
        String username = user.getFirstname();

        Like like = new Like();
        like.setRecipe(recipe);
        like.setUser(user);
        like.setUsername(username);
        likesRepository.save(like);
    }

    @Override
    @Transactional
    public void unlikeRecipe(Long recipeId, Long userId) {
        likesRepository.deleteByUserIdAndRecipeId(userId, recipeId);
    }

    @Override
    public Long countLikes(Long recipeId) {
        return likesRepository.countByRecipeId(recipeId);
    }

    @Override
    public boolean hasLikedRecipe(Long recipeId, Long userId) {
        return likesRepository.existsByUserIdAndRecipeId(userId, recipeId);
    }

    @Override
    public List<LikeDto> getByIdRecipe(Long id) {
        List<Like> likesList = likesRepository.findByRecipeIdOrderByIdDesc(id);
        return likeList(likesList);
    }
}
