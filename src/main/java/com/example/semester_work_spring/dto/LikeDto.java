package com.example.semester_work_spring.dto;

import com.example.semester_work_spring.models.Like;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LikeDto {
    private String username;

    public static LikeDto from (Like like) {
        return LikeDto.builder()
                .username(like.getUsername())
                .build();
    }

    public static List<LikeDto> likeList(List<Like> likes) {
        return likes.stream().map(LikeDto::from).collect(Collectors.toList());
    }
}
