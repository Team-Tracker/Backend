package io.github.teamtracker.utility;

import io.github.teamtracker.model.user.User;
import io.github.teamtracker.repository.UserRepository;

public class UserHelper {

    public static boolean userExists(Integer userId, UserRepository userRepository) {
        if (userRepository.findById(userId).isEmpty()) {
            return false;
        }

        User user = userRepository.findById(userId).get();

        if (user.isDeleted()) {
            return false;
        }

        return true;
    }
}