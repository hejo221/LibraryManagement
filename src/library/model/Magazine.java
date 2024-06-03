package library.model;

public class Magazine extends Media {
    private String publisher;

    public Magazine(String title, int releaseYear, String publisher) {
        super(title, releaseYear);
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Magazine - Media ID: " + getMediaID() + ", Title: " + getTitle() + ", Release Year: " + getReleaseYear() +
                ", Publisher: " + getPublisher();
    }
}