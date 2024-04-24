package library.controller;

import library.model.Media;
import library.model.Book;
import library.model.Magazine;
import library.model.Game;
import library.model.Game.GamePlatform;
import library.model.Movie;
import library.model.Series;

import java.util.HashMap;

public class MediaController {
    private HashMap<Integer, Media> mediaDB;

    public MediaController() {
        this.mediaDB = new HashMap<>();
    }

    public Book addNewBook(int mediaID, String title, int releaseYear, String author, int numberOfPages) {
        Book newBook = new Book(mediaID, title, releaseYear, author, numberOfPages);
        mediaDB.put(mediaID, newBook);
        return newBook;
    }

    public Magazine addNewMagazine(int mediaID, String title, int releaseYear, String publisher) {
        Magazine newMagazine = new Magazine(mediaID, title, releaseYear, publisher);
        mediaDB.put(mediaID, newMagazine);
        return newMagazine;
    }

    public Game addNewGame(int mediaID, String title, int releaseYear, String developer, GamePlatform gamePlatform) {
        Game newGame = new Game(mediaID, title, releaseYear, developer, gamePlatform);
        mediaDB.put(mediaID, newGame);
        return newGame;
    }

    public Movie addNewMovie(int mediaID, String title, int releaseYear, String director, int lengthInMinutes) {
        Movie newMovie = new Movie(mediaID, title, releaseYear, director, lengthInMinutes);
        mediaDB.put(mediaID, newMovie);
        return newMovie;
    }

    public Series addNewSeries(int mediaID, String title, int releaseYear, int numberOfSeason, int numberOfEpisodes) {
        Series newSeries = new Series(mediaID, title, releaseYear, numberOfSeason, numberOfEpisodes);
        mediaDB.put(mediaID, newSeries);
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

    public boolean updateSeriesNumberOfSeason(int id, int newNumberOfSeason) {
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
}