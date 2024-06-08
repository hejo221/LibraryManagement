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

    public Copy(int copyID, Media media, boolean isBorrowed, boolean isOverdue, LocalDate borrowDate, LocalDate returnDate, Customer borrower, Employee borrowedBy) {
        this.copyID = copyID;
        this.media = media;
        this.isBorrowed = isBorrowed;
        this.isOverdue = isOverdue;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.borrower = borrower;
        this.borrowedBy = borrowedBy;
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

    public static Copy readFromFile(String data) {
        String[] parts = data.split(",");
        if (parts.length < 13) {
            throw new IllegalArgumentException("Invalid data format.");
        }

        int copyID = Integer.parseInt(parts[0]);

        String mediaData = parts[1] + "," + parts[2] + "," + parts[3] + "," + parts[4] + "," + parts[5] + "," + parts[6];
        Media media = Media.readFromFile(mediaData);

        boolean isBorrowed = Boolean.parseBoolean(parts[7]);
        boolean isOverdue = Boolean.parseBoolean(parts[8]);

        LocalDate borrowDate = null;
        LocalDate returnDate = null;
        Customer borrower = null;
        Employee borrowedBy = null;

        if (parts.length > 13) {
            borrowDate = LocalDate.parse(parts[9]);
            returnDate = LocalDate.parse(parts[10]);

            String customerString = parts[11] + "," + parts[12] + "," + parts[13] + "," + parts[14];
            borrower = Customer.readFromFile(customerString);

            String employeeString = parts[15] + "," + parts[16] + "," + parts[17] + "," + parts[18] + "," + parts[19] + "," + parts[20];
            borrowedBy = Employee.readFromFile(employeeString);
        }

        return new Copy(copyID, media, isBorrowed, isOverdue, borrowDate, returnDate, borrower, borrowedBy);
    }

    public String writeToFile() {
        return getCopyID() + "," + getMedia().writeToFile() + "," + (isBorrowed() ? isBorrowed() + "," + isOverdue() + "," + getBorrowDate() + "," + getReturnDate() + "," +
                getBorrower().writeToFile() + "," + getBorrowedBy().writeToFile() : isBorrowed() + ",false,null,null,null,null");
    }

    @Override
    public String toString() {
        return "Copy - Copy ID: " + getCopyID() + ", Media: " + getMedia().getMediaID() + ", " + getMedia().getTitle()
                + ", Borrowed?: " + isBorrowed() + (isBorrowed() ? ", Borrow Date: " + getBorrowDate() + ", Return Date: "
                + getReturnDate() + ", Borrower (ID): " + getBorrower().getCustomerID() + ", Overdue?: " + isOverdue() : "");
    }
}
