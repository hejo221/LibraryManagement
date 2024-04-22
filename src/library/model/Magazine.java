package library.model;

public class Magazine extends Media {
    private String publisher;

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
}
