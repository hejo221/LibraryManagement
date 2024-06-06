package library.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Copy {
    private static final int MIN_COPY_ID = 100000;
    private static final int MAX_COPY_ID = 999999;
    private static final Set<Integer> allocatedCopyIDs = new HashSet<>();
    private static final Random random = new Random();

    private int copyID;
    private Media media;
    private boolean isBorrowed;
    private boolean isOverdue;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private Customer borrower;
    private Employee borrowedBy;

    public Copy(Media media) {
        this.copyID = generateCopyID();
        this.media = media;
        this.isBorrowed = false;
        this.isOverdue = false;
        this.borrowDate = null;
        this.returnDate = null;
        this.borrower = null;
        this.borrowedBy = null;
    }

    private int generateCopyID() {
        int randomID;

        do {
            randomID = random.nextInt(MAX_COPY_ID - MIN_COPY_ID + 1) + MIN_COPY_ID;
        } while (allocatedCopyIDs.contains(randomID));

        allocatedCopyIDs.add(randomID);
        return randomID;
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

    public boolean isOverdue() {
        return isOverdue;
    }

    public void setOverdue(boolean overdue) {
        isOverdue = overdue;
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

    @Override
    public String toString() {
        return "Copy - Copy ID: " + getCopyID() + ", Media: " + getMedia().getMediaID() + ", " + getMedia().getTitle()
                + ", Borrowed?: " + getBorrowDate() + (isBorrowed() ? ", Borrow Date: " + getBorrowDate() + ", Return Date: "
                + getReturnDate() + ", Borrower (ID): " + getBorrower().getCustomerID() + ", Overdue?: " + isOverdue() : "");
    }
}