package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.example.models.User;
import com.example.repositories.UserRepository;

import java.util.Optional;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

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

    /**
     * Delete a user from the database.
     * 
     * @param id the ID of the user to be deleted
     * @return the ID of the deleted user
     */
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteUser(@RequestParam Integer id) {
        userRepository.deleteById(id);
        return id.toString();
    }

    /**
     * Get all users from the database.
     * 
     * @return an iterable of all users
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Login a user by checking the username and password.
     * 
     * @param username the username of the user
     * @param password the plain text password of the user
     * @return the ID of the user if login is successful, -1 otherwise
     */
    @GetMapping(path = "/login")
    public @ResponseBody int login(@RequestParam String username, @RequestParam String password) {
        Iterable<User> users = userRepository.findByUsername(username);
        for (User user : users) {
            if (BCrypt.checkpw(password, user.getPassword())) {
                return user.getId();
            }
        }
        return -1;
    }

    /**
     * Resolve the username by user ID.
     * 
     * @param id the ID of the user
     * @return the username of the user, or null if not found
     */
    @GetMapping(path = "/resolveUsername")
    public @ResponseBody String getUsername(@RequestParam Integer id) {
        Iterable<String> usernames = userRepository.resolveUsername(id);
        if (!usernames.iterator().hasNext()) {
            return null;
        }
        return usernames.iterator().next();
    }
}