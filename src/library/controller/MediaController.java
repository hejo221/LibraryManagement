package library.controller;

import library.model.Media;
import library.model.Book;
import library.model.Magazine;
import library.model.Game;
import library.model.Game.GamePlatform;

import java.util.HashMap;

public class MediaController {
    private HashMap<Integer, Media> mediaDB;

    public MediaController() {
        this.mediaDB = new HashMap<>();
    }

    public Media addNewBook(int mediaID, String title, int releaseYear, String author, int numberOfPages) {
        Book newBook = new Book(mediaID, title, releaseYear, author, numberOfPages);
        mediaDB.put(mediaID, newBook);
        return newBook;
    }

    public Magazine addNewMagazine(int mediaID, String title, int releaseYear, String publisher) {
        Magazine newMagazine = new Magazine(mediaID, title, releaseYear, publisher);
        mediaDB.put(mediaID, newMagazine);
        return newMagazine;
    }

    public Media addNewGame(int mediaID, String title, int releaseYear, String developer, GamePlatform gamePlatform) {
        Game newGame = new Game(mediaID, title, releaseYear, developer, gamePlatform);
        mediaDB.put(mediaID, newGame);
        return newGame;
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

    public boolean updateGamePlatform(int id, GamePlatform newGamePlatform) {
        Media media = mediaDB.get(id);
        if (media instanceof Game) {
            ((Game) media).setGamePlatform(newGamePlatform);
            return true;
        }
        return false;
    }
}
