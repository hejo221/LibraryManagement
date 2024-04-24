package library.model;

public class Series extends Media {
    private int numberOfSeason;
    private int numberOfEpisodes;

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
}
