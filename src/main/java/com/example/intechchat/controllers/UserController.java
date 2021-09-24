package com.example.intechchat.controllers;

import com.example.intechchat.domain.User;
import com.example.intechchat.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/register", consumes = "application/json")
    public String registerUser(@Valid @RequestBody User newUser) {
        if (userRepository.findByUserName(newUser.getUserName()).isPresent()) throw new UsernameIsTakenException();

        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

        return String.format("Username %s registration successful", userRepository.save(newUser).getUserName());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User is already registered!")
    static class UsernameIsTakenException extends RuntimeException {
    }
}