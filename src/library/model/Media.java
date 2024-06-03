package library.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Media {
    private static final int MIN_MEDIA_ID = 100000;
    private static final int MAX_MEDIA_ID = 999999;
    private static final Set<Integer> allocatedMediaIDs = new HashSet<>();
    private static final Random random = new Random();

    private int mediaID;
    private String title;
    private int releaseYear;

    public Media(String title, int releaseYear) {
        this.mediaID = generateMediaID();
        this.title = title;
        this.releaseYear = releaseYear;
    }

    private int generateMediaID() {
        int randomID;

        do {
            randomID = random.nextInt(MAX_MEDIA_ID - MIN_MEDIA_ID + 1) + MIN_MEDIA_ID;
        } while (allocatedMediaIDs.contains(randomID));

        allocatedMediaIDs.add(randomID);
        return randomID;
    }

    public int getMediaID() {
        return mediaID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
