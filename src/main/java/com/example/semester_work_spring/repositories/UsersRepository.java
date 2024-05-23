package com.example.semester_work_spring.repositories;

import com.example.semester_work_spring.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByConfirmedCode(String confirmedCode);

}
