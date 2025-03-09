package io.github.teamtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.teamtracker.model.user.User;
import io.github.teamtracker.repository.UserRepository;
import io.github.teamtracker.utility.UserHelper;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Get all users from the database.
     * 
     * @return an iterable of all users
     */
    @GetMapping(path = "/")
    public @ResponseBody Iterable<User> getUsers() {
        return this.userRepository.findAll();
    }

    /**
     * Get all users from the database except deleted ones.
     * 
     * @return an iterable of all users
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // return this.userRepository.findAll();

        return UserHelper.findActiveUsers(userRepository);
    }

    /**
     * Get all users from the database except one.
     * 
     * @param id the ID of the user to exclude
     * @return an iterable of all users except the one with the given ID
     */
    @GetMapping(path = "/others")
    public @ResponseBody Iterable<User> getOthers(@RequestParam Integer id) {
        List<User> users = UserHelper.findActiveUsers(userRepository);

        for (User user : users) {
            if (user.getId().equals(id)) {
                users.remove(user);

                break;
            }
        }

        return users;
    }

    /**
     * Get a user from the database by ID.
     * 
     * @return the user with the given ID
     */
    @GetMapping(path = "/one")
    public @ResponseBody User getOne(@RequestParam Integer id) {
        return this.userRepository.one(id);
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

    /**
     * Resolve the user ID by username.
     * 
     * @param username the username of the user
     * @return the ID of the user, or -1 if not found
     * 
     */
    @GetMapping(path = "/resolveId")
    public @ResponseBody Integer getUserId(@RequestParam String username) {
        Integer id = this.userRepository.resolveId(username);

        if (id == null) {
            return -1;
        }

        return id;
    }

    /**
     * Check if a user exists in the database.
     * 
     * @param id the ID of the user
     * @return true if the user exists, false otherwise
     */
    @GetMapping(path = "/exists")
    public @ResponseBody Boolean getUser(@RequestParam Integer id) {
        return UserHelper.userExists(id, this.userRepository);
    }

    /**
     * Change the details of a user in the database.
     * 
     * @param username  the username of the user
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param email     the email of the user
     * @param phone     the phone number of the user
     * @param password  the password of the user
     * @return the ID of the new user
     */
    @PutMapping(path = "/update")
    public @ResponseBody User changeDetails(@RequestParam Integer id, @RequestParam String firstName,
            @RequestParam String lastName, @RequestParam String email, @RequestParam String phone) {
        User user = this.userRepository.findById(id).get();

        if (user == null) {
            return null;
        }

        if (user.isDeleted()) {
            return null;
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);

        this.userRepository.save(user);

        return user;
    }

    /**
     * Delete a user from the database.
     * 
     * @param id the ID of the user to be deleted
     * @return the ID of the deleted user
     */
    @DeleteMapping(path = "/delete")
    public @ResponseBody String deleteUser(@RequestParam Integer id) {
        // this.userRepository.deleteById(id);

        User user = this.userRepository.findById(id).get();

        if (user == null) {
            return null;
        }

        if (user.isDeleted()) {
            return null;
        }

        user.delete();

        this.userRepository.save(user);

        return id.toString();
    }
}