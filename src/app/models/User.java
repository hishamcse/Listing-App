package app.models;

import java.util.ArrayList;
import java.util.List;

public class User {

    private final String email;
    private final List<Movie> favorites;

    public User(String email) {
        this.email = email;
        this.favorites = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public List<Movie> getFavorites() {
        return favorites;
    }

    public void addFavorite(Movie movie) {
        favorites.add(movie);
    }

    public void removeFavorite(Movie movie) {
        favorites.remove(movie);
    }

    public boolean hasFavorite(Movie movie) {
        return favorites.contains(movie);
    }

    public String userDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("User: ").append(email).append("\n");
        sb.append("Favorite Movies: ").append("\n");
        for (Movie movie : favorites) {
            sb.append(movie).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
