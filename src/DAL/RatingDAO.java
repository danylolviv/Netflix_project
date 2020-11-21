package DAL;

import BE.Movie;
import BE.Rating;
import BE.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Rating> getAllRatings()
    {
        List<Rating> allRatings = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(new File(RATINGS_SOURCE))))
        {
            boolean hasLines = true;
            while(hasLines){
                String line = br.readLine();
                if(line==null)
                    hasLines=false;
                if(hasLines)
                {
                    try{  allRatings.add(makeObjectFromString(line));} catch (NumberFormatException e) {
                        //e.printStackTrace();
                        System.out.println("Number format exception: "+ line);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
