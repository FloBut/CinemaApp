package com.example.CinemaApp.A_controller;

import com.example.CinemaApp.B_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    public UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
