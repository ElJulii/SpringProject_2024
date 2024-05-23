package com.example.semester_work_spring.services;

import com.example.semester_work_spring.dto.UserDto;
import com.example.semester_work_spring.models.User;
import com.example.semester_work_spring.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.example.semester_work_spring.dto.UserDto.userList;

@Component
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public List<UserDto> getAllUsers() {
        return userList(usersRepository.findAll());
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return usersRepository.findByEmail(email).map(UserDto::from);
    }

    @Override
    public void deleteUser(Long userID) {
        usersRepository.deleteById(userID);
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = usersRepository.findById(userDto.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        usersRepository.save(user);
    }


}
