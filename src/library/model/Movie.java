package library.model;

public class Movie extends Media {
    private String director;
    private int lengthInMinutes;

    public Movie(String title, int releaseYear, String director, int lengthInMinutes) {
        super(title, releaseYear);
        this.director = director;
        this.lengthInMinutes = lengthInMinutes;
    }

    public Movie(int mediaID, String title, int releaseYear, String director, int lengthInMinutes) {
        super(mediaID, title, releaseYear);
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
    public String getMediaType() {
        return "Movie";
    }

    public static Movie readFromFile(String mediaString) {
        String[] parts = mediaString.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid data format.");
        }

        int mediaID = Integer.parseInt(parts[1]);
        String title = parts[2];
        int releaseYear = Integer.parseInt(parts[3]);
        String director = parts[4];
        int lengthInMinutes = Integer.parseInt(parts[5]);

        return new Movie(mediaID, title, releaseYear, director, lengthInMinutes);
    }

    public String writeToFile() {
        return getMediaType() + "," + getMediaID() + "," + getTitle() + "," + getReleaseYear() + "," + getDirector() + "," + getLengthInMinutes();
    }

    @Override
    public String toString() {
        return "Movie - Media ID: " + getMediaID() + ", Title: " + getTitle() + ", Release Year: " + getReleaseYear() +
                ", Director: " + getDirector() + ", Length in Minutes: " + getLengthInMinutes();
    }
}
