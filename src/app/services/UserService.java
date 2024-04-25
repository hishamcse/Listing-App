package app.services;

import app.models.Movie;
import app.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public User getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    public void addFavorite(String email, Movie movie) {
        User user = getUserByEmail(email);
        if(user == null) {
            throw new IllegalArgumentException("User not found");
        }

        user.addFavorite(movie);
    }

    public void removeFavorite(String email, Movie movie) {
        User user = getUserByEmail(email);
        if(user == null) {
            throw new IllegalArgumentException("User not found");
        }

        user.removeFavorite(movie);
    }

    public String getUserDetails(String email) {
        User user = getUserByEmail(email);
        if(user == null) {
            throw new IllegalArgumentException("User not found");
        }

        return user.userDetails();
    }

    public List<Movie> getFavoritesByQuery(String email, String query) {
        User user = getUserByEmail(email);
        if(user == null) {
            throw new IllegalArgumentException("User not found");
        }

        return user.getFavorites().stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(query) ||
                        movie.getCast().stream().anyMatch(query::equalsIgnoreCase) ||
                        movie.getCategory().equalsIgnoreCase(query))
                .sorted()
                .collect(Collectors.toList());
    }
}
