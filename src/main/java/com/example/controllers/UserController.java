package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    /**
     * Delete a user from the database.
     * 
     * @param id the ID of the user to be deleted
     * @return the ID of the deleted user
     */
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteUser(@RequestParam Integer id) {
        this.userRepository.deleteById(id);

        return id.toString();
    }

    /**
     * Get all users from the database.
     * 
     * @return an iterable of all users
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    /**
     * Get all users from the database except one.
     * 
     * @param id the ID of the user to exclude
     * @return an iterable of all users except the one with the given ID
     */
    @GetMapping(path = "/others")
    public @ResponseBody Iterable<User> getOtherUsers(@RequestParam Integer id) {
        Iterable<User> users = this.userRepository.findAll();

        for (User user : users) {
            if (user.getId() == id) {
                ((java.util.Collection<User>) users).remove(user);
                break;
            }
        }

        return users;
    }

    /**
     * Resolve the username by user ID.
     * 
     * @param id the ID of the user
     * @return the username of the user, or null if not found
     */
    @GetMapping(path = "/resolveUsername")
    public @ResponseBody String getUsername(@RequestParam Integer id) {
        String username = this.userRepository.resolveUsername(id);

        if (username == null) {
            return null;
        }

        return username;
    }
}