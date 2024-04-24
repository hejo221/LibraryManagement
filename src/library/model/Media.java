package library.model;

public abstract class Media {
    private int mediaID;
    private String title;
    private int releaseYear;

    public Media(int mediaID, String title, int releaseYear) {
        this.mediaID = mediaID;
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public int getMediaID() {
        return mediaID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
