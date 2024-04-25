package test;

import app.models.Movie;
import app.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        userService.registerUser("syed@gmail.com");
        userService.registerUser("sdfshdi@yahoo.com");
        Movie movie = new Movie("The Shawshank Redemption", List.of("Tim Robbins", "Morgan Freeman"), "Drama", "1994-09-22", 25000000);
        userService.addFavorite("sdfshdi@yahoo.com", movie);
        movie = new Movie("The Godfather", List.of("Marlon Brando", "Al Pacino"), "Crime", "1972-03-24", 6000000);
        userService.addFavorite("syed@gmail.com", movie);
    }

    @Test
    void registerUser() {
        assertTrue(userService.registerUser("sd@gmail.com"));
    }

    @Test
    void registerUserInvalidEmail() {
        assertFalse(userService.registerUser("sdf"));
    }

    @Test
    void registerUserEmptyEmail() {
        assertFalse(userService.registerUser(""));
    }

    @Test
    void registerUserAlreadyExists() {
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser("syed@gmail.com"));
    }

    @Test
    void getUserByEmail() {
        assertNotNull(userService.getUserByEmail("syed@gmail.com"));
    }

    @Test
    void addFavorite() {
        Movie movie = new Movie("The Shawshank Redemption", List.of("Tim Robbins", "Morgan Freeman"), "Drama", "1994-09-22", 25000000);
        userService.addFavorite("syed@gmail.com", movie);
        assertTrue(userService.getUserByEmail("syed@gmail.com").hasFavorite(movie));
    }

    @Test
    void addFavoriteUserNotFound() {
        Movie movie = new Movie("The Shawshank Redemption", List.of("Tim Robbins", "Morgan Freeman"), "Drama", "1994-09-22", 25000000);
        assertThrows(IllegalArgumentException.class, () -> userService.addFavorite("ssdas@yahoo.com", movie));
    }

    @Test
    void addFavoriteMovieAlreadyExists() {
        Movie movie = new Movie("The Shawshank Redemption", List.of("Tim Robbins", "Morgan Freeman"), "Drama", "1994-09-22", 25000000);
        assertThrows(IllegalArgumentException.class, () -> userService.addFavorite("sdfshdi@gmail.com", movie));
    }

    @Test
    void removeFavorite() {
        Movie movie = new Movie("The Shawshank Redemption", List.of("Tim Robbins", "Morgan Freeman"), "Drama", "1994-09-22", 25000000);
        userService.removeFavorite("syed@gmail.com", movie);
    }

    @Test
    void removeFavoriteUserNotFound() {
        Movie movie = new Movie("The Shawshank Redemption", List.of("Tim Robbins", "Morgan Freeman"), "Drama", "1994-09-22", 25000000);
        assertThrows(IllegalArgumentException.class, () -> userService.removeFavorite("sdghfhd@yahoo.com", movie));
    }

    @Test
    void removeFavoriteMovieNotFound() {
        Movie movie = new Movie("The Shawshank Redemption", List.of("Tim Robbins", "Morgan Freeman"), "Drama", "1994-09-22", 25000000);
        assertThrows(IllegalArgumentException.class, () -> userService.removeFavorite("sd@gmail.com", movie));
    }

    @Test
    void getUserDetails() {
        assertNotNull(userService.getUserDetails("syed@gmail.com"));
    }

    @Test
    void getUserDetailsUserNotFound() {
        assertThrows(IllegalArgumentException.class, () -> userService.getUserDetails("bnmvbnxb@gmail.com"));
    }

    @Test
    void getFavoritesByQuery() {
        assertNotNull(userService.getFavoritesByQuery("syed@gmail.com", "The Godfather"));
        assertNotNull(userService.getFavoritesByQuery("syed@gmail.com", "Al Pacino"));
        assertNotNull(userService.getFavoritesByQuery("syed@gmail.com", "Crime"));
        List<Movie> movies = userService.getFavoritesByQuery("syed@gmail.com", "Claymore");
        assertTrue(movies.isEmpty());
    }
}