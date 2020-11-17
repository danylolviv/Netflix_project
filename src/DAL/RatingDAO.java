package DAL;

import BE.Movie;
import BE.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RatingDAO implements IRatingDataAccess {
    private static final String RATINGS_SOURCE =
            "D:\\onedrive2\\OneDrive - Erhvervsakademi Sydvest\\" +
                    "week 45\\netfilx\\Netflix_project\\data\\ratings.txt";

    public void rate(Object selectedItem, Movie movie, User selectedUser) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(RATINGS_SOURCE, true))) {
            String ratingString = selectedUser.getID() + ","+  movie.getId() + "," + selectedItem;
            bw.append(ratingString);
            bw.newLine();
            // prepare for the next operations. its like
            //cleaning after yourself

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
