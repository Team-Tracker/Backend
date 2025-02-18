package io.github.teamtracker.utility;

import java.util.ArrayList;
import java.util.List;

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

    public static List<User> findActiveUsers(UserRepository userRepository) {
        Iterable<User> users = userRepository.findAll();

        List<User> activeUsers = new ArrayList<>();

        for (User user : users) {
            if (!user.isDeleted()) {
                activeUsers.add(user);
            }
        }

        return activeUsers;
    }
}