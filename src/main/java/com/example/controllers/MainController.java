package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.models.User;
import com.example.repositories.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping(path = "/main")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Login a user by checking the username and password.
     * 
     * @param username the username of the user
     * @param password the plain text password of the user
     * @return the ID of the user if login is successful, -1 otherwise
     */
    @GetMapping(path = "/login")
    public @ResponseBody int login(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username);

        if (BCrypt.checkpw(password, user.getPassword())) {
            return user.getId();
        }

        return -1;
    }

    /**
     * Add a user to the database (with a hashed password).
     * 
     * @param username          the username of the user
     * @param encryptedPassword the hashed password of the user
     * @return the ID of the newly created user
     */
    @PostMapping(path = "/add")
    public @ResponseBody String addUser(@RequestParam String username, @RequestParam String encryptedPassword) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
        return user.getId().toString();
    }

    /**
     * Add a user to the database (without hashing the password).
     * ! Do not use this method in production.
     * 
     * @param username the username of the user
     * @param password the plain text password of the user
     * @return the ID of the newly created user
     */
    @PostMapping(path = "/addUnsafe")
    public @ResponseBody String addUnsafeUser(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setUsername(username);
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return user.getId().toString();
    }

    /**
     * Change the password of a user in the database (with a hashed password).
     * 
     * @param id                the ID of the user
     * @param encryptedPassword the new hashed password of the user
     * @return the ID of the user whose password was changed
     */
    @PatchMapping(path = "/password")
    public @ResponseBody String changePassword(@RequestParam Integer id, @RequestParam String encryptedPassword) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(encryptedPassword);
            userRepository.save(user);
            return user.getId().toString();
        } else {
            return "User not found";
        }
    }

    /**
     * Change the password of a user in the database (without hashing the password).
     * ! Do not use this method in production.
     * 
     * @param id       the ID of the user
     * @param password the new plain text password of the user
     * @return the ID of the user whose password was changed
     */
    @PatchMapping(path = "/passwordUnsafe")
    public @ResponseBody String changePasswordUnsafe(@RequestParam Integer id, @RequestParam String password) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            user.setPassword(hashedPassword);
            userRepository.save(user);
            return user.getId().toString();
        } else {
            return "User not found";
        }
    }
}