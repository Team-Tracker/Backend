package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.models.User;
import com.example.repositories.UserRepository;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewUser(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);

        return user.getId().toString();
    }

    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteUser(@RequestParam Integer id) {
        userRepository.deleteById(id);

        return id.toString();
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/login")
    public @ResponseBody int login(@RequestParam String username, @RequestParam String password) {
        Iterable<User> users = userRepository.findByUsername(username);

        // ! Password should be hashed
        // Loop not necessary because name is unique
        for (User user : users) {
            if (user.getPassword().equals(password)) {
                return user.getId();
            }
        }

        return -1;
    }
}