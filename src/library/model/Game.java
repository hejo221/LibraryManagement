package library.model;

public class Game extends Media {
    private String developer;
    private GamePlatform gamePlatform;

    public Game(String title, int releaseYear, String developer, GamePlatform gamePlatform) {
        super(title, releaseYear);
        this.developer = developer;
        this.gamePlatform = gamePlatform;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public GamePlatform getGamePlatform() {
        return gamePlatform;
    }

    public void setGamePlatform(GamePlatform gamePlatform) {
        this.gamePlatform = gamePlatform;
    }

    @Override
    public String toString() {
        return "Game - Media ID: " + getMediaID() + ", Title: " + getTitle() + ", Release Year: " + getReleaseYear() +
                ", Developer: " + getDeveloper() + ", Platform: " + getGamePlatform();
    }

    public enum GamePlatform {
        PC, PLAYSTATION, XBOX, BOARD
    }
}