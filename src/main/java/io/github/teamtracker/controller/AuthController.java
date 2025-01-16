package io.github.teamtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.teamtracker.exception.LoginException;
import io.github.teamtracker.model.User;
import io.github.teamtracker.repository.UserRepository;
import io.github.teamtracker.utility.LoginHelper;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Login a user by checking the username and password.
     * Returns a JWT token if login is successful.
     * ? POST because we are sending sensitive information (password) in the request
     * body
     * 
     * @param username the username of the user
     * @param password the plain text password of the user
     * @return the ID of the user if login is successful, -1 otherwise
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username);

        try {
            String jwt = LoginHelper.login(user, password);

            return ResponseEntity.ok(jwt);
        } catch (LoginException exception) {
            return ResponseEntity.status(401).body(exception.getMessage());
        }
    }
}