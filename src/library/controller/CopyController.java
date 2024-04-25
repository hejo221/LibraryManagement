package library.controller;

import library.model.Copy;
import library.model.Customer;
import library.model.Employee;
import library.model.Media;

import java.util.HashMap;
import java.time.LocalDate;

public class CopyController {
    private HashMap<Integer, Copy> copyDB;

    public CopyController() {
        this.copyDB = new HashMap<>();
    }

    public Copy addNewCopy(int id, Media media) {
        Copy newCopy = new Copy(id, media);
        copyDB.put(id, newCopy);
        return newCopy;
    }

    public boolean removeCopy(int id) {
        if (copyDB.containsKey(id)) {
            copyDB.remove(id);
            return true;
        }
        return false;
    }

    public boolean borrowCopy(int id, Customer borrower, Employee borrowedBy) {
        if (copyDB.containsKey(id)) {
            Copy copy = copyDB.get(id);
            if (!copy.isBorrowed()) {
                copy.setBorrowed(true);
                copy.setBorrowDate(LocalDate.now());
                copy.setReturnDate(LocalDate.now().plusDays(28));
                copy.setBorrower(borrower);
                copy.setBorrowedBy(borrowedBy);
                return true;
            }
        }
        return false;
    }

    public boolean returnCopy(int id) {
        if (copyDB.containsKey(id)) {
            Copy copy = copyDB.get(id);
            if (copy.isBorrowed()) {
                copy.setBorrowed(false);
                copy.setBorrowDate(null);
                copy.setReturnDate(null);
                copy.setBorrower(null);
                copy.setBorrowedBy(null);
                return true;
            }
        }
        return false;
    }

    public boolean updateCopyBorrowDate(int id, LocalDate borrowDate) {
        if (copyDB.containsKey(id)) {
            Copy copy = copyDB.get(id);
            copy.setBorrowDate(borrowDate);
            return true;
        }
        return false;
    }

    public boolean updateCopyReturnDate(int id, LocalDate returnDate) {
        if (copyDB.containsKey(id)) {
            Copy copy = copyDB.get(id);
            copy.setReturnDate(returnDate);
            return true;
        }
        return false;
    }
}