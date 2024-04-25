package dataLoader;

import app.models.Movie;
import app.services.MovieService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MovieLoader {

    public static void loadMoviesFromFile(String filename, MovieService movieService) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 5) {
                    String[] cast = values[1].replaceAll("\"", "").split(";");
                    List<String> castList = Arrays.asList(cast);
                    Movie movie = new Movie(values[0], castList, values[2], values[3], parseBudget(values[4]));
                    movieService.addMovie(movie);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static double parseBudget(String budgetStr) {
        return Double.parseDouble(budgetStr.replaceAll("M", "")) * 1_000_000;
    }
}
