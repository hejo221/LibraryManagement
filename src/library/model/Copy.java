package library.model;

import java.time.LocalDate;

public class Copy {
    private int copyID;
    private Media media;
    private boolean isBorrowed;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Customer borrower;
    private Employee borrowedBy;

    public Copy(int copyID, Media media) {
        this.copyID = copyID;
        this.media = media;
        this.isBorrowed = false;
        this.borrowDate = null;
        this.returnDate = null;
        this.borrower = null;
        this.borrowedBy = null;
    }

    public int getCopyID() {
        return copyID;
    }

    public Media getMedia() {
        return media;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Customer getBorrower() {
        return borrower;
    }

    public void setBorrower(Customer borrower) {
        this.borrower = borrower;
    }

    public Employee getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(Employee borrowedBy) {
        this.borrowedBy = borrowedBy;
    }
}