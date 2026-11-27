package com.haider.springsecuritydb.controller;

import com.haider.springsecuritydb.controller.api.UserDetail;
import com.haider.springsecuritydb.serivce.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/hi")
    public String hi() {
        return "Hi, World!";
    }

    @PostMapping("/auth/add")
    public String addUser(@RequestBody UserDetail userDetail) {

        userService.addUser(userDetail.username(), userDetail.password());

        return "User added successfully!";
    }
}
