package library.model;

public class Book extends Media {
    private String author;
    private int numberOfPages;

    public Book(String title, int releaseYear, String author, int numberOfPages) {
        super(title, releaseYear);
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
    public String toString() {
        return "Book - Media ID: " + getMediaID() + ", Author: " + getTitle() + ", Release Year: " + getReleaseYear() +
                ", Author: " + getAuthor() + ", Number of Pages: " + getNumberOfPages();
    }
}