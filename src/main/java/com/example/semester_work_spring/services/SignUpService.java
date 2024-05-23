package com.example.semester_work_spring.services;

import com.example.semester_work_spring.dto.UserForm;

public interface SignUpService {
    void addUser(UserForm userForm);
    void confirmUser(String confirmedCode);
}
