package DAL.file;

import BE.Movie;
import BE.Rating;
import BE.User;
import DAL.IRatingDataAccess;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RatingDAO implements IRatingDataAccess {
    private static final String RATINGS_SOURCE =
            "D:\\onedrive2\\OneDrive - Erhvervsakademi Sydvest\\" +
                    "week 45\\netfilx\\Netflix_project\\data\\ratings.txt";
    private static final int RECORD_SIZE = Integer.BYTES * 3;

    public void rate(Object selectedItem, Movie movie, User selectedUser) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(RATINGS_SOURCE, true))) {
            String ratingString = selectedUser.getID() + ","+  movie.getId() + "," + selectedItem;
            bw.append(ratingString);
            bw.newLine();
            // prepare for the next operations

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Rating> getAllRatings()   {

        List<Rating> allRatings = new ArrayList<>();
        try {

            for (String line: Files.readAllLines(Path.of(RATINGS_SOURCE))){
                Rating r =makeObjectFromString(line);
                allRatings.add(r);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return allRatings;

    }

    private Rating makeObjectFromString(String line) {
        String[] splittedLine = line.split(",");
        int userID= Integer.parseInt(splittedLine[0]);
        int ratedMovieID = Integer.parseInt(splittedLine[1]);
        int rating = Integer.parseInt(splittedLine[2]);

        Rating rated = new Rating(userID, ratedMovieID, rating);
        return rated;

    }


}
