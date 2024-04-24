package app.services;

import app.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
    }

    private boolean validEmail(String email) {
        return email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }

    public boolean registerUser(String email) {
        if(email.isEmpty() || !validEmail(email)) return false;

        User user = new User(email);
        if(users.contains(user)) {
            throw new IllegalArgumentException("User already exists");
        }

        users.add(user);
        return true;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
}
