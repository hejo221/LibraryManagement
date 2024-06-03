package library.model;

public class Movie extends Media {
    private String director;
    private int lengthInMinutes;

    public Movie(String title, int releaseYear, String director, int lengthInMinutes) {
        super(title, releaseYear);
        this.director = director;
        this.lengthInMinutes = lengthInMinutes;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    @Override
    public String toString() {
        return "Movie - Media ID: " + getMediaID() + ", Title: " + getTitle() + ", Release Year: " + getReleaseYear() +
                ", Director: " + getDirector() + ", Length in Minutes: " + getLengthInMinutes();
    }
}
