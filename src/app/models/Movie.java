package app.models;

import java.time.LocalDate;
import java.util.List;

public class Movie implements Comparable<Movie> {

    private final String title;
    private final List<String> cast;
    private final String category;
    private final LocalDate releaseDate;
    private final double budget;

    public Movie(String title, List<String> cast, String category, String releaseDate, double budget) {
        this.title = title;
        this.cast = cast;
        this.category = category;
        this.releaseDate = LocalDate.parse(releaseDate); // LocalDate.parse("2021-01-01")
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

    public String movieDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title).append("\n");
        sb.append("Cast: ").append(cast).append("\n");
        sb.append("Category: ").append(category).append("\n");
        sb.append("Release Date: ").append(releaseDate).append("\n");
        sb.append("Budget: ").append(budget).append("\n");
        return sb.toString();
    }
}
