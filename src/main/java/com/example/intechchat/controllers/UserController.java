package com.example.intechchat.controllers;

import com.example.intechchat.domain.User;
import com.example.intechchat.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/register", consumes = "application/json")
    public String registerUser(@Valid @RequestBody User newUser) {
        if (userRepository.findByUserName(newUser.getUserName()).isPresent()) throw new UsernameIsTakenException();

        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

        return String.format("Username %s registration successful", userRepository.save(newUser).getUserName());
    }

    @GetMapping("/username")
    public String getName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User already exists!")
    static class UsernameIsTakenException extends RuntimeException {
    }
}