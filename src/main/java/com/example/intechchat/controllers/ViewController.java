package com.example.intechchat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping(path = "/login")
    public String login() {
        return "login.html";
    }

    @GetMapping(path = "/register")
    public String register() {
        return "register.html";
    }
}
