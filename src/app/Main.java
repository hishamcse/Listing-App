package app;

import app.services.MovieService;
import app.services.UserService;
import dataLoader.MovieLoader;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        MovieService movieService = new MovieService();

        MovieLoader.loadMoviesFromFile("src/data/movies.csv", movieService);

        System.out.println("Welcome to the Movie App!");

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Search all movies");
            System.out.println("3. See movie details");
            System.out.println("4. Add movie to favorites");
            System.out.println("5. Remove movie from favorites");
            System.out.println("6. See personal details");
            System.out.println("7. Search favorites");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            String email, title, query;

            switch (option) {
                case 1:
                    System.out.print("Enter your email: ");
                    email = scanner.nextLine();
                    if(userService.registerUser(email)) {
                        System.out.println("User registered successfully!");
                    } else {
                        System.out.println("User already exists!");
                    }
                    break;
                case 2:
                    System.out.print("Enter search query: ");
                    query = scanner.nextLine();
                    movieService.getMoviesByQuery(query).forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Enter movie title: ");
                    title = scanner.nextLine();
                    movieService.getMovies().stream()
                            .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                            .findFirst()
                            .ifPresent(System.out::println);
                    break;
                case 4:
                    System.out.print("Enter your email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter movie title: ");
                    title = scanner.nextLine();
                    movieService.getMovies().stream()
                            .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                            .findFirst()
                            .ifPresent(movie -> userService.addFavorite(email, movie));
                    break;
                case 5:
                    System.out.print("Enter your email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter movie title: ");
                    title = scanner.nextLine();
                    movieService.getMovies().stream()
                            .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                            .findFirst()
                            .ifPresent(movie -> userService.removeFavorite(email, movie));
                    break;
                case 6:
                    System.out.print("Enter your email: ");
                    email = scanner.nextLine();
                    System.out.println(userService.getUserDetails(email));
                    break;
                case 7:
                    System.out.print("Enter your email: ");
                    email = scanner.nextLine();
                    System.out.print("Enter search query: ");
                    query = scanner.nextLine();
                    userService.getFavoritesByQuery(email, query).forEach(System.out::println);
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}