package test;

import app.models.Movie;
import app.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MovieServiceTest {

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        movieService = new MovieService();
        movieService.addMovie(new Movie("The Shawshank Redemption", List.of("Tim Robbins", "Morgan Freeman"), "Drama", "1994-09-22", 25000000));
        movieService.addMovie(new Movie("The Godfather", List.of("Marlon Brando", "Al Pacino"), "Crime", "1972-03-24", 6000000));
        movieService.addMovie(new Movie("The Dark Knight", List.of("Christian Bale", "Heath Ledger"), "Action", "2008-07-18", 185000000));
    }

    @Test
    void addMovie() {
        movieService.addMovie(new Movie("The Godfather: Part II", List.of("Al Pacino", "Robert De Niro"), "Crime", "1974-12-20", 13000000));
        assert movieService.getMovies().size() == 4;
    }

    @Test
    void removeMovie() {
        movieService.removeMovie(new Movie("The Godfather: Part II", List.of("Al Pacino", "Robert De Niro"), "Crime", "1974-12-20", 13000000));
        assert movieService.getMovies().size() == 3;
    }

    @Test
    void getMovies() {
        List<Movie> movies = movieService.getMovies();
        for (Movie movie : movies) {
            System.out.println(movie.movieDetails());
        }
        assert movies.size() == 3;
    }

    @Test
    void getMoviesByTitle() {
        List<Movie> movies = movieService.getMoviesByQuery("The Godfather");
        for (Movie movie : movies) {
            System.out.println(movie.movieDetails());
        }
        assert movies.size() == 1;
    }

    @Test
    void getMoviesByCast() {
        List<Movie> movies = movieService.getMoviesByQuery("Al Pacino");
        for (Movie movie : movies) {
            System.out.println(movie.movieDetails());
        }
        assert movies.size() == 1;
    }

    @Test
    void getMoviesByCategory() {
        List<Movie> movies = movieService.getMoviesByQuery("Crime");
        for (Movie movie : movies) {
            System.out.println(movie.movieDetails());
        }
        assert movies.size() == 1;
    }

    @Test
    void getMoviesNotFound() {
        List<Movie> movies = movieService.getMoviesByQuery("The Godfather: Part III");
        assert movies.isEmpty();
    }
}