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

    public List<Rating> getAllRatings() {
        List<Rating> ratingList = new ArrayList<>();
        List<String> ratingLines = null;
        try {
             ratingLines = Files.readAllLines(Path.of(RATINGS_SOURCE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String line: ratingLines)
        {
            String[] split = line.split(",");
            ratingList.add(new Rating(Integer.parseInt(split[0]),
                    Integer.parseInt(split[1]), Integer.parseInt(split[2])));
        }
        return ratingList;
    }
}
