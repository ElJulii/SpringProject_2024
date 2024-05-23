package com.example.semester_work_spring.services;

import com.example.semester_work_spring.dto.UserForm;
import com.example.semester_work_spring.models.Role;
import com.example.semester_work_spring.models.State;
import com.example.semester_work_spring.models.User;
import com.example.semester_work_spring.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    private MailService mailService;
    @Autowired
    private  SmsService smsService;
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addUser(UserForm form) {
        User user = User.builder()
                .email(form.getEmail())
                .password(passwordEncoder.encode(form.getPassword()))
                .firstname(form.getFirstname())
                .lastname(form.getLastname())
                .state(State.NOT_CONFIRMED)
                .confirmedCode(UUID.randomUUID().toString())
                .phone(form.getPhone())
                .role(Role.USER)
                .build();
        smsService.sendSms(form.getPhone(), "Welcome " + form.getFirstname() + " to Ecuadorian Tasty Food, the best web side of Ecuador!");
        mailService.sendEmailForConfirm(user.getEmail(), user.getConfirmedCode());
        usersRepository.save(user);

    }

    @Override
    public void confirmUser(String confirmedCode) {
        User user = usersRepository.findByConfirmedCode(confirmedCode);
        if (user != null) {
            user.setState(State.CONFIRMED);
            usersRepository.save(user);
        } else {
            throw new RuntimeException("No se encontró el usuario con el código de confirmación dado: " + confirmedCode);
        }
    }
}
