package io.github.teamtracker.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // return this.userRepository.findAll();

        Iterable<User> users = this.userRepository.findAll();

        for (User user : users) {
            if (user.isDeleted()) {
                ((Collection<User>) users).remove(user);

                break;
            }
        }

        return users;
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
                ((Collection<User>) users).remove(user);

                break;
            }
        }

        for (User user : users) {
            if (user.isDeleted()) {
                ((Collection<User>) users).remove(user);

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

    @GetMapping(path = "/exists")
    public @ResponseBody Boolean getUser(@RequestParam Integer id) {
        return UserHelper.userExists(id, this.userRepository);
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