package test;

import app.models.Movie;
import app.services.MovieService;

import java.util.List;

class MovieServiceTest {

    private MovieService movieService;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        movieService = new MovieService();
        movieService.addMovie(new Movie("The Shawshank Redemption", List.of("Tim Robbins", "Morgan Freeman"), "Drama", "1994-09-22", 25000000));
        movieService.addMovie(new Movie("The Godfather", List.of("Marlon Brando", "Al Pacino"), "Crime", "1972-03-24", 6000000));
        movieService.addMovie(new Movie("The Dark Knight", List.of("Christian Bale", "Heath Ledger"), "Action", "2008-07-18", 185000000));
    }

    @org.junit.jupiter.api.Test
    void addMovie() {
        movieService.addMovie(new Movie("The Godfather: Part II", List.of("Al Pacino", "Robert De Niro"), "Crime", "1974-12-20", 13000000));
        assert movieService.getMovies().size() == 4;
    }

    @org.junit.jupiter.api.Test
    void removeMovie() {
        movieService.removeMovie(new Movie("The Godfather: Part II", List.of("Al Pacino", "Robert De Niro"), "Crime", "1974-12-20", 13000000));
        assert movieService.getMovies().size() == 3;
    }

    @org.junit.jupiter.api.Test
    void getMovies() {
        List<Movie> movies = movieService.getMovies();
        for (Movie movie : movies) {
            System.out.println(movie.movieDetails());
        }
        assert movies.size() == 3;
    }

    @org.junit.jupiter.api.Test
    void getMoviesByTitle() {
        List<Movie> movies = movieService.getMoviesByQuery("The Godfather");
        for (Movie movie : movies) {
            System.out.println(movie.movieDetails());
        }
        assert movies.size() == 1;
    }

    @org.junit.jupiter.api.Test
    void getMoviesByCast() {
        List<Movie> movies = movieService.getMoviesByQuery("Al Pacino");
        for (Movie movie : movies) {
            System.out.println(movie.movieDetails());
        }
        assert movies.size() == 1;
    }

    @org.junit.jupiter.api.Test
    void getMoviesByCategory() {
        List<Movie> movies = movieService.getMoviesByQuery("Crime");
        for (Movie movie : movies) {
            System.out.println(movie.movieDetails());
        }
        assert movies.size() == 1;
    }

    @org.junit.jupiter.api.Test
    void getMoviesNotFound() {
        List<Movie> movies = movieService.getMoviesByQuery("The Godfather: Part III");
        assert movies.isEmpty();
    }
}