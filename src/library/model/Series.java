package library.model;

public class Series extends Media {
    private int numberOfSeason;
    private int numberOfEpisodes;

    public Series(String title, int releaseYear, int numberOfSeason, int numberOfEpisodes) {
        super(title, releaseYear);
        this.numberOfSeason = numberOfSeason;
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public Series(int mediaID, String title, int releaseYear, int numberOfSeason, int numberOfEpisodes) {
        super(mediaID, title, releaseYear);
        this.numberOfSeason = numberOfSeason;
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getNumberOfSeason() {
        return numberOfSeason;
    }

    public void setNumberOfSeason(int numberOfSeason) {
        this.numberOfSeason = numberOfSeason;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    @Override
    public String getMediaType() {
        return "Series";
    }

    public static Series readFromFile(String mediaString) {
        String[] parts = mediaString.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid data format.");
        }

        int mediaID = Integer.parseInt(parts[1]);
        String title = parts[2];
        int releaseYear = Integer.parseInt(parts[3]);
        int numberOfSeason = Integer.parseInt(parts[4]);
        int numberOfEpisodes = Integer.parseInt(parts[5]);

        return new Series(mediaID, title, releaseYear, numberOfSeason, numberOfEpisodes);
    }

    public String writeToFile() {
        return getMediaType() + "," + getMediaID() + "," + getTitle() + "," + getReleaseYear() + "," + getNumberOfSeason() + "," + getNumberOfEpisodes();
    }

    @Override
    public String toString() {
        return "Series - Media ID: " + getMediaID() + ", Title: " + getTitle() + ", Release Year: " + getReleaseYear() +
                ", Number of Seasons: " + getNumberOfSeason() + ", Number of Episodes: " + getNumberOfEpisodes();
    }
}
