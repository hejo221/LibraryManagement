package library.model;

public class Game extends Media {
    private String developer;
    private GamePlatform gamePlatform;

    public Game(int mediaID, String title, int releaseYear, String developer, GamePlatform gamePlatform) {
        super(mediaID, title, releaseYear);
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

    public enum GamePlatform {
        PC, PLAYSTATION, XBOX, BOARD
    }
}
