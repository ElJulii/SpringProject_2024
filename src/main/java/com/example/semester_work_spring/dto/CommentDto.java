package com.example.semester_work_spring.dto;

import com.example.semester_work_spring.models.Comment;
import com.example.semester_work_spring.models.User;
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
public class CommentDto {
    private long id;
    private String comment;
    private String username;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .username(comment.getUsername())
                .build();
    }

    public static List<CommentDto> commentList(List<Comment> comments) {
        return comments.stream()
                .map(CommentDto::from)
                .collect(Collectors.toList());
    }
}
