package com.example.semester_work_spring.services;

import com.example.semester_work_spring.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<UserDto> getAllUsers();
    Optional<UserDto> findByEmail(String email);

    void deleteUser(Long userID);

    void updateUser(UserDto userDto);
}
