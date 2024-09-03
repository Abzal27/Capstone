package za.ac.cput.controller;

/**
 * UserController.java
 *Controller of User Class
 *Author:Moegamat Isgak Abzal
 *Student Number: 221321810
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.User;
import za.ac.cput.service.impl.UserServiceImpl;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @CrossOrigin(origins = "http://localhost:3315")
    @PostMapping("/register")
    public User register(@RequestBody User registrationRequest) {
        User createdUser = userService.create(registrationRequest);
        System.out.println("User registered: " + createdUser.getUserName());
        return createdUser;
    }

    @PostMapping("/login")
    public String login(@RequestBody User loginRequest) {
        String username = loginRequest.getUserName();
        return "User " + username + " logged in successfully";
    }

    @GetMapping("/profile/{userId}")
    public User readUser(@PathVariable Integer userId) {
        User readUser = userService.read(userId);
        if (readUser != null) {
            System.out.println("User profile retrieved: " + readUser.getUserName());
        } else {
            System.out.println("User not found with ID: " + userId);
        }
        return readUser;
    }

    @GetMapping("/{userId}")
    public User read(@PathVariable Integer userId) {
        User user = userService.read(userId);
        if (user != null) {
            System.out.println("User retrieved: " + user.getUserName());
        } else {
            System.out.println("User not found with ID: " + userId);
        }
        return user;
    }
}

