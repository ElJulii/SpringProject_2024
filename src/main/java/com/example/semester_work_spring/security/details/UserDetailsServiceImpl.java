package com.example.semester_work_spring.security.details;

import com.example.semester_work_spring.models.User;
import com.example.semester_work_spring.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = usersRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return new UserDetailsImpl(userOptional.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
