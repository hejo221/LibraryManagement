package library.view;

import library.controller.LibraryController;
import library.model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MediaView {
    private LibraryController libraryController;
    private Scanner scanner;

    public MediaView(LibraryController libraryController) {
        this.libraryController = libraryController;
        this.scanner = new Scanner(System.in);
    }

    public void displayGeneralOptions() {
        scanner.nextLine();

        System.out.println("\nYour Options are: ");
        System.out.println("1: Print all Media");
        System.out.println("2: Find specific Media");
        System.out.println("3: Manage Books");
        System.out.println("4: Manage Magazines");
        System.out.println("5: Manage Games");
        System.out.println("6: Manage Movies");
        System.out.println("7: Manage Series");
        System.out.println("8: Remove Media");
        System.out.println("9: Go back to main menu");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                printAllMedia();
                break;
            case 2:
                findMediaByID();
                break;
            case 3:
                displayBookOptions();
                break;
            case 4:
                displayMagazineOptions();
                break;
            case 5:
                displayGameOptions();
                break;
            case 6:
                displayMovieOptions();
                break;
            case 7:
                displaySeriesOptions();
                break;
            case 8:
                removeMedia();
                break;
            case 9:
                MainView mainView = new MainView(libraryController);
                mainView.displayOptions();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    public void displayBookOptions() {
        scanner.nextLine();

        System.out.println("\nYour options are: ");
        System.out.println("1: Add new Book");
        System.out.println("2: Update Book Title");
        System.out.println("3: Update Book Release Year");
        System.out.println("4: Update Book Author");
        System.out.println("5: Update Book Number of Pages");
        System.out.println("6: Go back to Media menu");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addNewBook();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                displayGeneralOptions();
                break;
            default:
                break;
        }
    }

    public void displayMagazineOptions() {
        scanner.nextLine();

        System.out.println("\nYour options are: ");
        System.out.println("1: Add new Magazine");
        System.out.println("2: Update Magazine Title");
        System.out.println("3: Update Magazine Release Year");
        System.out.println("4: Update Magazine Publisher");
        System.out.println("5: Go back to Media menu");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addNewMagazine();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                displayGeneralOptions();
                break;
            default:
                break;
        }
    }

    public void displayGameOptions() {
        scanner.nextLine();

        System.out.println("\nYour options are: ");
        System.out.println("1: Add new Game");
        System.out.println("2: Update Game Title");
        System.out.println("3: Update Game Release Year");
        System.out.println("4: Update Game Developer");
        System.out.println("5: Update Game Platform");
        System.out.println("6: Go back to Media menu");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addNewGame();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                displayGeneralOptions();
                break;
            default:
                break;
        }
    }

    public void displayMovieOptions() {
        scanner.nextLine();

        System.out.println("\nYour options are: ");
        System.out.println("1: Add new Movie");
        System.out.println("2: Update Movie Title");
        System.out.println("3: Update Movie Release Year");
        System.out.println("4: Update Movie Director");
        System.out.println("5: Update Movie Length");
        System.out.println("6: Go back to Media menu");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addNewMovie();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                displayGeneralOptions();
                break;
            default:
                break;
        }
    }

    public void displaySeriesOptions() {
        scanner.nextLine();

        System.out.println("\nYour options are: ");
        System.out.println("1: Add new Series");
        System.out.println("2: Update Series Title");
        System.out.println("3: Update Series Release Year");
        System.out.println("4: Update Series Seasons");
        System.out.println("5: Update Series Episodes");
        System.out.println("6: Go back to Media menu");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addNewSeries();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                displayGeneralOptions();
                break;
            default:
                break;
        }
    }

    public void printAllMedia() {
        libraryController.getMediaController().printAllMedia();
        displayGeneralOptions();
    }

    public void findMediaByID() {
        System.out.println("Searching for specific media: ");
        scanner.nextLine();

        try {
            System.out.println("Enter the Media ID: ");
            int id = scanner.nextInt();

            Media media = libraryController.getMediaController().findMedia(id);
            if (media != null) {
                System.out.println("Media found: " + media);
            } else {
                System.out.println("Media not found.");
            }
        } catch (InputMismatchException exception) {
            System.out.println("Invalid input. Please enter a numeric media ID.");
            scanner.nextLine();
        }

        displayGeneralOptions();
    }

    public void removeMedia() {
        System.out.println("Removing Media: ");
        scanner.nextLine();

        try {
            System.out.println("Enter the Media ID to remove: ");
            int id = scanner.nextInt();

            Media media = libraryController.getMediaController().findMedia(id);
            if (media != null) {
                System.out.println("Media found" + media);
                System.out.println("Are you sure that you want to remove this media? (yes/no): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("yes")) {
                    boolean removeSuccess = libraryController.getMediaController().removeMedia(id);
                    if (removeSuccess) {
                        System.out.println("Media removed successfully.");
                    } else {
                        System.out.println("Failed to remove Media.");
                    }
                } else {
                    System.out.println("Media removal canceled.");
                }
            } else {
                System.out.println("Media not found.");
            }
        } catch (InputMismatchException exception) {
            System.out.println("Invalid input. Please enter a numeric Media ID.");
            scanner.nextLine();
        }

        displayGeneralOptions();
    }

    public void addNewBook() {
        System.out.println("Adding new book: ");
        scanner.nextLine();

        String title;
        while (true) {
            System.out.println("Enter the title of the book: ");
            title = scanner.nextLine();
            if (!title.trim().isEmpty()) {
                break;
            } else {
                System.out.println("Title cannot be empty. Please enter a valid title.");
            }
        }

        int releaseYear;
        while (true) {
            try {
                System.out.println("Enter the release year of the book: ");
                releaseYear = scanner.nextInt();
                if (releaseYear < 0) {
                    throw new InputMismatchException("Release Year must not be negative.");
                } else {
                    scanner.nextLine();
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }

        String author;
        while (true) {
            System.out.println("Enter the author of the book: ");
            author = scanner.nextLine();
            if (author.matches(".*\\d.*")) {
                System.out.println("Numeral values are not allowed in names. Please enter a valid author.");
            } else {
                break;
            }
        }

        int numberOfPages;
        while (true) {
            try {
                System.out.println("Enter the number of pages of the book: ");
                numberOfPages = scanner.nextInt();
                if (numberOfPages < 0) {
                    throw new InputMismatchException("Number of Pages must not be negative.");
                } else {
                    scanner.nextLine();
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }

        Media book = libraryController.getMediaController().addNewBook(title, releaseYear, author, numberOfPages);

        if (book != null) {
            System.out.println("Book has successfully been added.");
            System.out.println("New Media: " + book);
        } else {
            System.out.println("Failed to add the new book.");
        }

        displayGeneralOptions();
    }

    public void addNewMagazine() {
        System.out.println("Adding new magazine: ");
        scanner.nextLine();

        String title;
        while (true) {
            System.out.println("Enter the title of the magazine: ");
            title = scanner.nextLine();
            if (!title.trim().isEmpty()) {
                break;
            } else {
                System.out.println("Title cannot be empty.");
            }
        }

        int releaseYear;
        while (true) {
            try {
                System.out.println("Enter the release year of the magazine: ");
                releaseYear = scanner.nextInt();
                if (releaseYear < 0) {
                    throw new InputMismatchException("Release Year must not be negative.");
                } else {
                    scanner.nextLine();
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }

        String publisher;
        while (true) {
            System.out.println("Enter the publisher of the magazine: ");
            publisher = scanner.nextLine();
            if (publisher.matches(".*\\d.*")) {
                System.out.println("Numeral values are not allowed in names. Please enter a valid author.");
            } else {
                break;
            }
        }

        Media magazine = libraryController.getMediaController().addNewMagazine(title, releaseYear, publisher);

        if (magazine != null) {
            System.out.println("Magazine has successfully been added.");
            System.out.println("New Media: " + magazine);
        } else {
            System.out.println("Failed to add the new magazine.");
        }

        displayGeneralOptions();
    }

    public void addNewGame() {
        System.out.println("Adding new game: ");
        scanner.nextLine();

        String title;
        while (true) {
            System.out.println("Enter the title of the game: ");
            title = scanner.nextLine();
            if (!title.trim().isEmpty()) {
                break;
            } else {
                System.out.println("Title cannot be empty. Please enter a valid title.");
            }
        }

        int releaseYear;
        while (true) {
            try {
                System.out.println("Enter the release year of the game: ");
                releaseYear = scanner.nextInt();
                if (releaseYear < 0) {
                    throw new InputMismatchException("Release Year must not be negative.");
                } else {
                    scanner.nextLine();
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }

        String director;
        while (true) {
            System.out.println("Enter the director of the movie: ");
            director = scanner.nextLine();
            if (!director.trim().isEmpty()) {
                System.out.println("Developer can not be empty.");
            } else {
                break;
            }
        }

        int lengthInMinutes;
        while (true) {
            try {
                System.out.println("Enter the length in minutes of the movue: ");
                lengthInMinutes = scanner.nextInt();
                if (lengthInMinutes < 0) {
                    throw new InputMismatchException("Length in minutes must not be negative.");
                } else {
                    scanner.nextLine();
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }

        Media movie = libraryController.getMediaController().addNewMovie(title, releaseYear, director, lengthInMinutes);

        if (movie != null) {
            System.out.println("Movie has successfully been added.");
            System.out.println("New Media: " + movie);
        } else {
            System.out.println("Failed to add the new movie.");
        }

        displayGeneralOptions();
    }

    public void addNewMovie() {
        System.out.println("Adding new movie: ");
        scanner.nextLine();

        String title;
        while (true) {
            System.out.println("Enter the title of the movie: ");
            title = scanner.nextLine();
            if (!title.trim().isEmpty()) {
                break;
            } else {
                System.out.println("Title cannot be empty. Please enter a valid title.");
            }
        }

        int releaseYear;
        while (true) {
            try {
                System.out.println("Enter the release year of the movie: ");
                releaseYear = scanner.nextInt();
                if (releaseYear < 0) {
                    throw new InputMismatchException("Release Year must not be negative.");
                } else {
                    scanner.nextLine();
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }

        String author;
        while (true) {
            System.out.println("Enter the author of the book: ");
            author = scanner.nextLine();
            if (author.matches(".*\\d.*")) {
                System.out.println("Numeral values are not allowed in names. Please enter a valid author.");
            } else {
                break;
            }
        }

        int numberOfPages;
        while (true) {
            try {
                System.out.println("Enter the number of pages of the book: ");
                numberOfPages = scanner.nextInt();
                if (numberOfPages < 0) {
                    throw new InputMismatchException("Number of Pages must not be negative.");
                } else {
                    scanner.nextLine();
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }

        Media book = libraryController.getMediaController().addNewBook(title, releaseYear, author, numberOfPages);

        if (book != null) {
            System.out.println("Book has successfully been added.");
            System.out.println("New Media: " + book);
        } else {
            System.out.println("Failed to add the new book.");
        }

        displayGeneralOptions();
    }

    public void addNewSeries() {
        System.out.println("Adding new series: ");
        scanner.nextLine();

        String title;
        while (true) {
            System.out.println("Enter the title of the series: ");
            title = scanner.nextLine();
            if (!title.trim().isEmpty()) {
                break;
            } else {
                System.out.println("Title cannot be empty. Please enter a valid title.");
            }
        }

        int releaseYear;
        while (true) {
            try {
                System.out.println("Enter the release year of the series: ");
                releaseYear = scanner.nextInt();
                if (releaseYear < 0) {
                    throw new InputMismatchException("Release Year must not be negative.");
                } else {
                    scanner.nextLine();
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }

        int numberOfSeasons;
        while (true) {
            try {
                System.out.println("Enter the number of seasons of the series: ");
                numberOfSeasons = scanner.nextInt();
                if (numberOfSeasons < 0) {
                    throw new InputMismatchException("Number of seaons must not be negative.");
                } else {
                    scanner.nextLine();
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }

        int numberOfEpisodes;
        while (true) {
            try {
                System.out.println("Enter the number of episodes of the series: ");
                numberOfEpisodes = scanner.nextInt();
                if (numberOfEpisodes < 0) {
                    throw new InputMismatchException("Number of episodes must not be negative.");
                } else {
                    scanner.nextLine();
                    break;
                }
            } catch (InputMismatchException exception) {
                System.out.println(exception.getMessage());
                scanner.nextLine();
            }
        }

        Media series = libraryController.getMediaController().addNewSeries(title, releaseYear, numberOfSeasons, numberOfEpisodes);

        if (series != null) {
            System.out.println("Series has successfully been added.");
            System.out.println("New Media: " + series);
        } else {
            System.out.println("Failed to add the new series.");
        }

        displayGeneralOptions();
    }
}