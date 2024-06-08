package library.model;

public class Magazine extends Media {
    private String publisher;

    public Magazine(String title, int releaseYear, String publisher) {
        super(title, releaseYear);
        this.publisher = publisher;
    }

    public Magazine(int mediaID, String title, int releaseYear, String publisher) {
        super(mediaID, title, releaseYear);
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String getMediaType() {
        return "Magazine";
    }

    public static Magazine readFromFile(String mediaString) {
        String[] parts = mediaString.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid data format.");
        }

        int mediaID = Integer.parseInt(parts[1]);
        String title = parts[2];
        int releaseYear = Integer.parseInt(parts[3]);
        String publisher = parts[4];

        return new Magazine(mediaID, title, releaseYear, publisher);
    }

    public String writeToFile() {
        return getMediaType() + "," + getMediaID() + "," + getTitle() + "," + getReleaseYear() + "," + getPublisher();
    }

    @Override
    public String toString() {
        return "Magazine - Media ID: " + getMediaID() + ", Title: " + getTitle() + ", Release Year: " + getReleaseYear() +
                ", Publisher: " + getPublisher();
    }
}
