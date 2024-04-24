package app.services;

import app.models.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieService {

    private final List<Movie> movies;

    public MovieService() {
        this.movies = new ArrayList<>();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Movie> getMoviesByQuery(String query) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(query) ||
                        movie.getCast().stream().anyMatch(query::equalsIgnoreCase) ||
                        movie.getCategory().equalsIgnoreCase(query))
                .sorted()
                .collect(Collectors.toList());
    }
}
