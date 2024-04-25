package library.controller;

import library.model.Copy;
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

    public boolean updateCopyBorrowStatus(int id, boolean isBorrowed) {
        if (copyDB.containsKey(id)) {
            Copy copy = copyDB.get(id);
            copy.setBorrowed(isBorrowed);
            return true;
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
