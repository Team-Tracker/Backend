package io.github.teamtracker.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.teamtracker.model.user.User;
import io.github.teamtracker.repository.UserRepository;

@Controller
@RequestMapping(path = "/old")
public class OldMainController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Add a user to the database (with a hashed password).
     * 
     * @param username       the username of the user
     * @param hashedPassword the hashed password of the user
     * @return the ID of the newly created user
     */
    @PostMapping(path = "/add")
    public @ResponseBody String addUser(@RequestParam String username, @RequestParam String hashedPassword) {
        User user = new User();

        user.setUsername(username);
        user.setPassword(hashedPassword);

        this.userRepository.save(user);

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

        this.userRepository.save(user);

        return user.getId().toString();
    }

    /**
     * Change the password of a user in the database (with a hashed password).
     * 
     * @param id             the ID of the user
     * @param hashedPassword the new hashed password of the user
     * @return the ID of the user whose password was changed
     */
    @PatchMapping(path = "/password")
    public @ResponseBody String changePassword(@RequestParam Integer id, @RequestParam String hashedPassword) {
        Optional<User> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setPassword(hashedPassword);
            this.userRepository.save(user);

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
        Optional<User> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            user.setPassword(hashedPassword);

            this.userRepository.save(user);

            return user.getId().toString();
        } else {
            return "User not found";
        }
    }
}