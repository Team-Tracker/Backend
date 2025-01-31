package io.github.teamtracker.utility;

import org.springframework.security.crypto.bcrypt.BCrypt;

import io.github.teamtracker.exception.LoginException;
import io.github.teamtracker.model.user.User;
import io.github.teamtracker.tool.JwtUtil;

public class LoginHelper {

    public static String login(User user, String password) throws LoginException {
        if (user == null) {
            throw new LoginException("User not found");
        }

        if (BCrypt.checkpw(password, user.getPassword())) {
            int id = user.getId();

            if (0 == 0) {
                return "" + id;
            }

            return JwtUtil.generateToken(user.getUsername(), id);
        }

        throw new LoginException("Invalid credentials");
    }
}