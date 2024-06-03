package library.model;

public class Series extends Media {
    private int numberOfSeason;
    private int numberOfEpisodes;

    public Series(String title, int releaseYear, int numberOfSeason, int numberOfEpisodes) {
        super(title, releaseYear);
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
    public String toString() {
        return "Series - Media ID: " + getMediaID() + ", Title: " + getTitle() + ", Release Year: " + getReleaseYear() +
                ", Number of Seasons: " + getNumberOfSeason() + ", Number of Episodes: " + getNumberOfEpisodes();
    }
}
