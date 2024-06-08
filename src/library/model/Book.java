package library.model;

public class Book extends Media {
    private String author;
    private int numberOfPages;

    public Book(String title, int releaseYear, String author, int numberOfPages) {
        super(title, releaseYear);
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public Book (int mediaID, String title, int releaseYear, String author, int numberOfPages) {
        super(mediaID, title, releaseYear);
        this.author = author;
        this.numberOfPages = numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String getMediaType() {
        return "Book";
    }

    public static Book readFromFile(String mediaString) {
        String[] parts = mediaString.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid data format.");
        }

        int mediaID = Integer.parseInt(parts[1]);
        String title = parts[2];
        int releaseYear = Integer.parseInt(parts[3]);
        String author = parts[4];
        int numberOfPages = Integer.parseInt(parts[5]);

        return new Book(mediaID, title, releaseYear, author, numberOfPages);
    }

    public String writeToFile() {
        return getMediaType() + "," + getMediaID() + "," + getTitle() + "," + getReleaseYear() + "," + getAuthor() + "," + getNumberOfPages();
    }


    @Override
    public String toString() {
        return "Book - Media ID: " + getMediaID() + ", Author: " + getTitle() + ", Release Year: " + getReleaseYear() +
                ", Author: " + getAuthor() + ", Number of Pages: " + getNumberOfPages();
    }
}
