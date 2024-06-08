package library.view;

import library.controller.LibraryController;
import library.model.*;
import library.util.InputUtil;
import library.util.SortUtil;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class MediaView {
    private LibraryController libraryController;
    private Scanner scanner;
    private InputUtil inputUtil;

    public MediaView(LibraryController libraryController) {
        this.libraryController = libraryController;
        this.scanner = new Scanner(System.in);
        this.inputUtil = new InputUtil(scanner);
    }

    public void displayGeneralOptions() {
        scanner.nextLine();

        System.out.println("\nYour Options are: ");
        System.out.println("1: Print all media (unsorted)");
        System.out.println("2: Print all media (sorted by title)");
        System.out.println("3: Print all media (sorted by release year)");
        System.out.println("4: Find specific media");
        System.out.println("5: Manage books");
        System.out.println("6: Manage magazines");
        System.out.println("7: Manage games");
        System.out.println("8: Manage movies");
        System.out.println("9: Manage series");
        System.out.println("10: Remove media");
        System.out.println("11: Go back to main menu");

        System.out.println("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                printAllMedia();
                break;
            case 2:
                printAllMediaSortedAlphabetically();
                break;
            case 3:
                printAllMediaSortedNumerically();
                break;
            case 4:
                findMediaByID();
                break;
            case 5:
                displayBookOptions();
                break;
            case 6:
                displayMagazineOptions();
                break;
            case 7:
                displayGameOptions();
                break;
            case 8:
                displayMovieOptions();
                break;
            case 9:
                displaySeriesOptions();
                break;
            case 10:
                removeMedia();
                break;
            case 11:
                MainView mainView = new LibraryView(libraryController).getMainView();
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
                updateMediaTitle();
                break;
            case 3:
                updateMediaReleaseYear();
                break;
            case 4:
                updateBookAuthor();
                break;
            case 5:
                updateBookNumberOfPages();
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
                updateMediaTitle();
                break;
            case 3:
                updateMediaReleaseYear();
                break;
            case 4:
                updateMagazinePublisher();
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
                updateMediaTitle();
                break;
            case 3:
                updateMediaReleaseYear();
                break;
            case 4:
                updateGameDeveloper();
                break;
            case 5:
                updateGamePlatform();
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
                updateMediaTitle();
                break;
            case 3:
                updateMediaReleaseYear();
                break;
            case 4:
                updateMovieDirector();
                break;
            case 5:
                updateMovieLength();
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
                updateMediaTitle();
                break;
            case 3:
                updateMediaReleaseYear();
                break;
            case 4:
                updateSeriesNumberOfSeasons();
                break;
            case 5:
                updateSeriesNumberOfEpisodes();
                break;
            case 6:
                displayGeneralOptions();
                break;
            default:
                break;
        }
    }

    private void printAllMedia() {
        libraryController.getMediaController().printAllMedia(libraryController.getMediaController().getMediaDB());
        displayGeneralOptions();
    }

    private void printAllMediaSortedAlphabetically() {
        LinkedHashMap<Integer, Media> sortedMedia = SortUtil.sortByComparator(libraryController.getMediaController().getMediaDB(), new SortUtil.MediaTitleComparator());
        libraryController.getMediaController().printAllMedia(sortedMedia);
    }

    private void printAllMediaSortedNumerically() {
        LinkedHashMap<Integer, Media> sortedMedia = SortUtil.sortByComparator(libraryController.getMediaController().getMediaDB(), new SortUtil.MediaReleaseYearComparator());
        libraryController.getMediaController().printAllMedia(sortedMedia);
    }

    private void findMediaByID() {
        System.out.println("Searching for specific media by ID: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the media ID to search for: ", "Invalid input. Please enter a numeric media ID.");

        Media media = libraryController.getMediaController().findMedia(id);

        if (media != null) {
            System.out.println("Media found: " + media);
        } else {
            System.out.println("Media not found.");
        }

        displayGeneralOptions();
    }

    public void removeMedia() {
        System.out.println("Removing Media: ");

        int id = inputUtil.getIntInput("Enter the media ID to remove: ", "Invalid input. Please enter a numeric media ID.");

        Media media = libraryController.getMediaController().findMedia(id);
        if (media != null) {
            System.out.println("Media found: " + media);
            String confirmation = inputUtil.getStringInput("Are you sure that you want to remove this media? (yes/no): ", "Invalid input. Please enter 'yes' or 'no'.", false);

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

        displayGeneralOptions();
    }

    private void addNewBook() {
        System.out.println("Adding new book: \n");
        scanner.nextLine();

        String title = inputUtil.getStringInput("Enter the title of the book: ", "The title of the book cannot be empty.", true);
        int releaseYear = inputUtil.getIntInput("Enter the release year of the book: ", "The release year of the book must not be negative");
        String author = inputUtil.getStringInput("Enter the author of the book: ", "The author of the book cannot be empty.", false);
        int numberOfPages = inputUtil.getIntInput("Enter the number of pages of the book: ", "The number of pages of the book must not be negative");

        Media book = libraryController.getMediaController().addNewBook(title, releaseYear, author, numberOfPages);

        if (book != null) {
            System.out.println("The book has successfully been added.");
            System.out.println("New Media: " + book);
        } else {
            System.out.println("Failed to add the new book.");
        }

        displayGeneralOptions();
    }

    private void addNewMagazine() {
        System.out.println("Adding new magazine: \n");
        scanner.nextLine();

        String title = inputUtil.getStringInput("Enter the title of the magazine: ", "The title of the magazine cannot be empty.", true);
        int releaseYear = inputUtil.getIntInput("Enter the release year of the magazine: ", "The release year of the magazine must not be negative");
        String publisher = inputUtil.getStringInput("Enter the publisher of the magazine: ", "The publisher of the magazine cannot be empty.", false);

        Media magazine = libraryController.getMediaController().addNewMagazine(title, releaseYear, publisher);

        if (magazine != null) {
            System.out.println("The magazine has successfully been added.");
            System.out.println("New Media: " + magazine);
        } else {
            System.out.println("Failed to add the new magazine.");
        }

        displayGeneralOptions();
    }

    private void addNewGame() {
        System.out.println("Adding new game: \n");
        scanner.nextLine();

        String title = inputUtil.getStringInput("Enter the title of the game: ", "The title of the game cannot be empty.", true);
        int releaseYear = inputUtil.getIntInput("Enter the release year of the game: ", "The release year of the game must not be negative");
        String developer = inputUtil.getStringInput("Enter the developer of the game: ", "The developer of the game cannot be empty.", false);
        String platform = inputUtil.getStringInput("Enter the platform of the game (PC, PLAYSTATION, XBOX, BOARD): ", "The platform of the game cannot be empty.", false).toUpperCase();

        Game.GamePlatform gamePlatform = Game.GamePlatform.valueOf(platform);
        Media game = libraryController.getMediaController().addNewGame(title, releaseYear, developer, gamePlatform);

        if (game != null) {
            System.out.println("The game has successfully been added.");
            System.out.println("New Media: " + game);
        } else {
            System.out.println("Failed to add the new game.");
        }

        displayGeneralOptions();
    }

    private void addNewMovie() {
        System.out.println("Adding new movie: \n");
        scanner.nextLine();

        String title = inputUtil.getStringInput("Enter the title of the movie: ", "The title of the movie cannot be empty.", true);
        int releaseYear = inputUtil.getIntInput("Enter the release year of the movie: ", "The release year of the movie must not be empty");
        String director = inputUtil.getStringInput("Enter the director of the movie: ", "The director of the movie cannot be empty.", false);
        int lengthInMinutes = inputUtil.getIntInput("Enter the length in minutes of the movie: ", "The length in minutes of the movie must not be negative");

        Media movie = libraryController.getMediaController().addNewMovie(title, releaseYear, director, lengthInMinutes);

        if (movie != null) {
            System.out.println("The movie has successfully been added.");
            System.out.println("New Media: " + movie);
        } else {
            System.out.println("Failed to add the new movie.");
        }

        displayGeneralOptions();
    }

    private void addNewSeries() {
        System.out.println("Adding new series: \n");
        scanner.nextLine();

        String title = inputUtil.getStringInput("Enter the title of the series: ", "The title of the series cannot be empty.", true);
        int releaseYear = inputUtil.getIntInput("Enter the release year of the series: ", "The release year of the series must not be empty");
        int numberOfSeasons = inputUtil.getIntInput("Enter the number of seasons of the series: ", "The number of seasons of the series must not be negative.");
        int numberOfEpisodes = inputUtil.getIntInput("Enter the number of episodes of the series: ", "The number of episodes of the series must not be negative");

        Media series = libraryController.getMediaController().addNewSeries(title, releaseYear, numberOfSeasons, numberOfEpisodes);

        if (series != null) {
            System.out.println("The series has successfully been added.");
            System.out.println("New Media: " + series);
        } else {
            System.out.println("Failed to add the new series.");
        }

        displayGeneralOptions();
    }

    private void updateMediaTitle() {
        System.out.println("Updating the title of a media: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the media ID to update the title: ", "The media ID must not be negative.");
        Media media = libraryController.getMediaController().findMedia(id);

        if (media != null) {
            System.out.println("The following media will be updated: " + media);
            String newTitle = inputUtil.getStringInput("Enter the new title: ", "The new title cannot be empty.", true);

            boolean updateSuccess = libraryController.getMediaController().updateMediaTitle(id, newTitle);
            if (updateSuccess) {
                System.out.println("The title has been updated successfully.");
            } else {
                System.out.println("Failed to update the title.");
            }
        } else {
            System.out.println("Media not found.");
        }

        displayGeneralOptions();
    }

    private void updateMediaReleaseYear() {
        System.out.println("Updating the release year of a media: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the media ID to update the release year: ", "The media ID must not be negative");
        Media media = libraryController.getMediaController().findMedia(id);

        if (media != null) {
            System.out.println("The following media will be updated: " + media);
            int newReleaseYear = inputUtil.getIntInput("Enter the new release year: ", "The new release year must not be negative.");

            boolean updateSuccess = libraryController.getMediaController().updateMediaReleaseYear(id, newReleaseYear);
            if (updateSuccess) {
                System.out.println("The release year has been updated successfully.");
            } else {
                System.out.println("Failed to update the release year.");
            }
        } else {
            System.out.println("Media not found.");
        }

        displayGeneralOptions();
    }

    private void updateBookAuthor() {
        System.out.println("Updating the author of a book: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the media ID to update the author: ", "The media ID must not be negative");
        Media media = libraryController.getMediaController().findMedia(id);

        if (media instanceof Book book) {
            System.out.println("The following media will be updated: " + book);
            String newAuthor = inputUtil.getStringInput("Enter the new author: ", "The new author cannot be empty.", false);

            boolean updateSuccess = libraryController.getMediaController().updateBookAuthor(id, newAuthor);
            if (updateSuccess) {
                System.out.println("The author has been updated successfully.");
            } else {
                System.out.println("Failed to update the author.");
            }
        } else {
            System.out.println("Media not found.");
        }

        displayGeneralOptions();
    }

    private void updateBookNumberOfPages() {
        System.out.println("Updating the number of pages of a book: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the media ID to update the number of pages: ", "The media ID must not be negative");
        Media media = libraryController.getMediaController().findMedia(id);

        if (media instanceof Book book) {
            System.out.println("The following media will be updated: " + book);
            int numberOfPages = inputUtil.getIntInput("Enter the new author: ", "The new number of pages must not be negative.");

            boolean updateSuccess = libraryController.getMediaController().updateBookPages(id, numberOfPages);
            if (updateSuccess) {
                System.out.println("The number of pages has been updated successfully.");
            } else {
                System.out.println("Failed to update the number of pages.");
            }
        } else {
            System.out.println("Media not found.");
        }

        displayGeneralOptions();
    }

    private void updateMagazinePublisher() {
        System.out.println("Updating the publisher of a magazine: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the media ID to update the publisher: ", "The media ID must not be negative");
        Media media = libraryController.getMediaController().findMedia(id);

        if (media instanceof Magazine magazine) {
            System.out.println("The following magazine will be updated: " + magazine);
            String newPublisher = inputUtil.getStringInput("Enter the new publisher: ", "The new publisher cannot be empty.", true);

            boolean updateSuccess = libraryController.getMediaController().updateMagazinePublisher(id, newPublisher);
            if (updateSuccess) {
                System.out.println("The publisher has been updated successfully.");
            } else {
                System.out.println("Failed to update the publisher.");
            }
        } else {
            System.out.println("Media not found.");
        }

        displayGeneralOptions();
    }

    private void updateGameDeveloper() {
        System.out.println("Updating the developer of a game: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the media ID to update the developer: ", "The media ID must not be negative");
        Media media = libraryController.getMediaController().findMedia(id);

        if (media instanceof Game game) {
            System.out.println("The following game will be updated: " + game);
            String newDeveloper = inputUtil.getStringInput("Enter the new developer: ", "The new developer cannot be empty.", true);

            boolean updateSuccess = libraryController.getMediaController().updateGameDeveloper(id, newDeveloper);
            if (updateSuccess) {
                System.out.println("The developer has been updated successfully.");
            } else {
                System.out.println("Failed to update the developer.");
            }
        } else {
            System.out.println("Media not found.");
        }

        displayGeneralOptions();
    }

    private void updateGamePlatform() {
        System.out.println("Updating the platform of a game: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the media ID to update the platform: ", "The media ID must not be negative");
        Media media = libraryController.getMediaController().findMedia(id);

        if (media instanceof Game game) {
            System.out.println("The following game will be updated: " + game);
            String newPlatform = inputUtil.getStringInput("Enter the new platform (PC, PLAYSTATION, XBOX, BOARD): ", "The new platform cannot be empty.", true).toUpperCase();

            Game.GamePlatform gamePlatform = Game.GamePlatform.valueOf(newPlatform);
            boolean updateSuccess = libraryController.getMediaController().updateGamePlatform(id, gamePlatform);
            if (updateSuccess) {
                System.out.println("The platform has been updated successfully.");
            } else {
                System.out.println("Failed to update the platform.");
            }
        } else {
            System.out.println("Media not found.");
        }

        displayGeneralOptions();
    }

    private void updateMovieDirector() {
        System.out.println("Updating the director of a movie: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the media ID to update the director: ", "The media ID must not be negative");
        Media media = libraryController.getMediaController().findMedia(id);

        if (media instanceof Movie movie) {
            System.out.println("The following movie will be updated: " + movie);
            String newDirector = inputUtil.getStringInput("Enter the new director: ", "The new director cannot be empty.", false);

            boolean updateSuccess = libraryController.getMediaController().updateMovieDirector(id, newDirector);
            if (updateSuccess) {
                System.out.println("The director has been updated successfully.");
            } else {
                System.out.println("Failed to update the director.");
            }
        } else {
            System.out.println("Media not found.");
        }

        displayGeneralOptions();
    }

    private void updateMovieLength() {
        System.out.println("Updating the length in minutes of a movie: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the media ID to update the length in minutes: ", "The media ID must not be negative");
        Media media = libraryController.getMediaController().findMedia(id);

        if (media instanceof Movie movie) {
            System.out.println("The following movie will be updated: " + movie);
            int newLength = inputUtil.getIntInput("Enter the new length in minutes: ", "The new length in minutes must not be negative.");

            boolean updateSuccess = libraryController.getMediaController().updateMovieLengthInMinutes(id, newLength);
            if (updateSuccess) {
                System.out.println("The length in minutes has been updated successfully.");
            } else {
                System.out.println("Failed to update the length in minutes.");
            }
        } else {
            System.out.println("Media not found.");
        }

        displayGeneralOptions();
    }

    private void updateSeriesNumberOfSeasons() {
        System.out.println("Updating the number of seasons of a series: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the media ID to update the number of seasons: ", "The media ID must not be negative");
        Media media = libraryController.getMediaController().findMedia(id);

        if (media instanceof Series series) {
            System.out.println("The following series will be updated: " + series);
            int newNumberOfSeasons = inputUtil.getIntInput("Enter the new number of seasons: ", "The new number of seasons must not be negative.");

            boolean updateSuccess = libraryController.getMediaController().updateSeriesNumberOfSeasons(id, newNumberOfSeasons);
            if (updateSuccess) {
                System.out.println("The number of seasons has been updated successfully.");
            } else {
                System.out.println("Failed to update the number of seasons.");
            }
        } else {
            System.out.println("Media not found.");
        }

        displayGeneralOptions();
    }

    private void updateSeriesNumberOfEpisodes() {
        System.out.println("Updating the number of episodes of a series: ");
        scanner.nextLine();

        int id = inputUtil.getIntInput("Enter the media ID to update the number of episodes: ", "The media ID must not be negative");
        Media media = libraryController.getMediaController().findMedia(id);

        if (media instanceof Series series) {
            System.out.println("The following series will be updated: " + series);
            int newNumberOfEpisodes = inputUtil.getIntInput("Enter the new number of episodes: ", "The new number of episodes must not be negative.");

            boolean updateSuccess = libraryController.getMediaController().updateSeriesNumberOfEpisodes(id, newNumberOfEpisodes);
            if (updateSuccess) {
                System.out.println("The number of episodes has been updated successfully.");
            } else {
                System.out.println("Failed to update the number of episodes.");
            }
        } else {
            System.out.println("Media not found.");
        }

        displayGeneralOptions();
    }
}
