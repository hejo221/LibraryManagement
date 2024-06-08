package library.controller;

import library.exceptions.FileReadException;
import library.model.Media;
import library.model.Book;
import library.model.Magazine;
import library.model.Game;
import library.model.Game.GamePlatform;
import library.model.Movie;
import library.model.Series;
import library.util.ReadUtil;

import java.util.HashMap;

public class MediaController {
    private HashMap<Integer, Media> mediaDB;

    public MediaController() {
        this.mediaDB = new HashMap<>();
        readMedia();
    }

    private void readMedia() {
        try {
            mediaDB = ReadUtil.readMediaFromFile();
        } catch (FileReadException exception) {
            System.out.println("Error reading media from file: " + exception.getMessage());
        }
    }

    public void printAllMedia(HashMap<Integer, Media> mediaDB) {
        if (mediaDB.isEmpty()) {
            System.out.println("No media found.");
        } else {
            for (Media media : mediaDB.values()) {
                System.out.println(media);
            }
        }
    }

    public Book addNewBook(String title, int releaseYear, String author, int numberOfPages) {
        Book newBook = new Book(title, releaseYear, author, numberOfPages);
        mediaDB.put(newBook.getMediaID(), newBook);
        return newBook;
    }

    public Magazine addNewMagazine(String title, int releaseYear, String publisher) {
        Magazine newMagazine = new Magazine(title, releaseYear, publisher);
        mediaDB.put(newMagazine.getMediaID(), newMagazine);
        return newMagazine;
    }

    public Game addNewGame(String title, int releaseYear, String developer, GamePlatform gamePlatform) {
        Game newGame = new Game(title, releaseYear, developer, gamePlatform);
        mediaDB.put(newGame.getMediaID(), newGame);
        return newGame;
    }

    public Movie addNewMovie(String title, int releaseYear, String director, int lengthInMinutes) {
        Movie newMovie = new Movie(title, releaseYear, director, lengthInMinutes);
        mediaDB.put(newMovie.getMediaID(), newMovie);
        return newMovie;
    }

    public Series addNewSeries(String title, int releaseYear, int numberOfSeason, int numberOfEpisodes) {
        Series newSeries = new Series(title, releaseYear, numberOfSeason, numberOfEpisodes);
        mediaDB.put(newSeries.getMediaID(), newSeries);
        return newSeries;
    }

    public boolean removeMedia(int id) {
        if (mediaDB.containsKey(id)) {
            mediaDB.remove(id);
            return true;
        }
        return false;
    }

    public Media findMedia(int id) {
        return mediaDB.get(id);
    }

    public boolean updateMediaTitle(int id, String newTitle) {
        Media media = mediaDB.get(id);
        if (media != null) {
            media.setTitle(newTitle);
            return true;
        }
        return false;
    }

    public boolean updateMediaReleaseYear(int id, int newReleaseYear) {
        Media media = mediaDB.get(id);
        if (media != null) {
            media.setReleaseYear(newReleaseYear);
            return true;
        }
        return false;
    }

    public boolean updateBookAuthor(int id, String newAuthor) {
        Media media = mediaDB.get(id);
        if (media instanceof Book) {
            ((Book) media).setAuthor(newAuthor);
            return true;
        }
        return false;
    }

    public boolean updateBookPages(int id, int newNumberOfPages) {
        Media media = mediaDB.get(id);
        if (media instanceof Book) {
            ((Book) media).setNumberOfPages(newNumberOfPages);
            return true;
        }
        return false;
    }

    public boolean updateMagazinePublisher(int id, String newPublisher) {
        Media media = mediaDB.get(id);
        if (media instanceof Magazine) {
            ((Magazine) media).setPublisher(newPublisher);
            return true;
        }
        return false;
    }

    public boolean updateGameDeveloper(int id, String newDeveloper) {
        Media media = mediaDB.get(id);
        if (media instanceof Game) {
            ((Game) media).setDeveloper(newDeveloper);
            return true;
        }
        return false;
    }

    public boolean updateGamePlatform(int id, GamePlatform newGamePlatform) {
        Media media = mediaDB.get(id);
        if (media instanceof Game) {
            ((Game) media).setGamePlatform(newGamePlatform);
            return true;
        }
        return false;
    }

    public boolean updateMovieDirector(int id, String newDirector) {
        Media media = mediaDB.get(id);
        if (media instanceof Movie) {
            ((Movie) media).setDirector(newDirector);
            return true;
        }
        return false;
    }

    public boolean updateMovieLengthInMinutes(int id, int newLengthInMinutes) {
        Media media = mediaDB.get(id);
        if (media instanceof Movie) {
            ((Movie) media).setLengthInMinutes(newLengthInMinutes);
            return true;
        }
        return false;
    }

    public boolean updateSeriesNumberOfSeasons(int id, int newNumberOfSeason) {
        Media media = mediaDB.get(id);
        if (media instanceof Series) {
            ((Series) media).setNumberOfSeason(newNumberOfSeason);
            return true;
        }
        return false;
    }

    public boolean updateSeriesNumberOfEpisodes(int id, int newNumberOfEpisodes) {
        Media media = mediaDB.get(id);
        if (media instanceof Series) {
            ((Series) media).setNumberOfEpisodes(newNumberOfEpisodes);
            return true;
        }
        return false;
    }
    public HashMap<Integer, Media> getMediaDB() {
        return mediaDB;
    }
}
