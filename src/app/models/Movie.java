package app.models;

import java.util.Date;
import java.util.List;

public class Movie implements Comparable<Movie> {

    private final String title;
    private final List<String> cast;
    private final String category;
    private final Date releaseDate;
    private final double budget;

    public Movie(String title, List<String> cast, String category, String releaseDate, double budget) {
        this.title = title;
        this.cast = cast;
        this.category = category;
        this.releaseDate = new Date(releaseDate);
        this.budget = budget;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getCast() {
        return cast;
    }

    public String getCategory() {
        return category;
    }

    public String getReleaseDate() {
        return releaseDate.toString();
    }

    public double getBudget() {
        return budget;
    }

    @Override
    public int compareTo(Movie other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return title + " (" + releaseDate + ")";
    }
}
