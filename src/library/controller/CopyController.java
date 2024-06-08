package library.controller;

import library.exceptions.FileReadException;
import library.model.Copy;
import library.model.Customer;
import library.model.Employee;
import library.model.Media;
import library.util.ReadUtil;

import java.util.HashMap;
import java.time.LocalDate;

public class CopyController {
    private HashMap<Integer, Copy> copyDB;

    public CopyController() {
        this.copyDB = new HashMap<>();
        readCopies();
    }

    private void readCopies() {
        try {
            copyDB = ReadUtil.readCopiesFromFile();
        } catch (FileReadException exception) {
            System.out.println("Error reading copies from file: " + exception.getMessage());
        }
    }

    public void printAllCopies(HashMap<Integer, Copy> copyDB) {
        if (copyDB.isEmpty()) {
            System.out.println("No copies found.");
        } else {
            for (Copy copy : copyDB.values()) {
                System.out.println(copy);
            }
        }
    }

    public Copy findCopy(int id) {
        return copyDB.get(id);
    }

    public Copy addNewCopy(Media media) {
        Copy newCopy = new Copy(media);
        copyDB.put(newCopy.getCopyID(), newCopy);
        return newCopy;
    }

    public boolean removeCopy(int id) {
        if (copyDB.containsKey(id)) {
            Copy copy = copyDB.get(id);
            if (copy.isBorrowed()) {
                return false;
            } else {
                copyDB.remove(id);
                return true;
            }
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

    public boolean extendCopyReturnDate(int id) {
        if (copyDB.containsKey(id)) {
            Copy copy = copyDB.get(id);
            LocalDate returnDate = copy.getReturnDate().plusDays(28);
            copy.setReturnDate(returnDate);
            return true;
        }
        return false;
    }

    public HashMap<Integer, Copy> getCopyDB() {
        return copyDB;
    }
}
