package library.model;

public class Game extends Media {
    private String developer;
    private GamePlatform gamePlatform;

    public Game(String title, int releaseYear, String developer, GamePlatform gamePlatform) {
        super(title, releaseYear);
        this.developer = developer;
        this.gamePlatform = gamePlatform;
    }

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

    @Override
    public String getMediaType() {
        return "Game";
    }

    public static Game readFromFile(String mediaString) {
        String[] parts = mediaString.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid data format.");
        }

        int mediaID = Integer.parseInt(parts[1]);
        String title = parts[2];
        int releaseYear = Integer.parseInt(parts[3]);
        String developer = parts[4];
        String platform = parts[5];

        GamePlatform gamePlatform = GamePlatform.valueOf(platform);

        return new Game(mediaID, title, releaseYear, developer, gamePlatform);
    }

    public String writeToFile() {
        return getMediaType() + "," + getMediaID() + "," + getTitle() + "," + getReleaseYear() + "," + getDeveloper() + "," + getGamePlatform();
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
