package BLL.util;

import BE.Movie;
import BE.Rating;
import DAL.MovieDAO;
import DAL.RatingDAO;
import DAL.UserDAO;

import java.util.*;

public class MovieRecommenderBasic {

        private MovieDAO movieDAO;
        private UserDAO userDAO;
        private RatingDAO ratingDAO;

        public MovieRecommenderBasic() {
            movieDAO = new MovieDAO();
            userDAO = new UserDAO();
        }


    public double averageRating(int ratedMovieID)
    {
        Movie movie = movieDAO.findMovieByID(ratedMovieID);

        List<Rating> allRatings = ratingDAO.getAllRatings();
        double sumRating=0.0;
        int iterations=0;

        for(Rating rating: allRatings)
        {
            if(rating.getRatedMovieID()==movie.getId())
            {
                sumRating+=rating.getRating();
                iterations++;
            }
        }
        return sumRating/iterations;
    }

    /**
     * Calculate the average rating for all the books
     * in the library
     *
     *add them to the ArraylIst
     *
     * sort
     */
    public void addRatings()
    {
        List<Rating> allRatingsList = ratingDAO.getAllRatings();
        HashMap<Rating, Double> allRatingsHashMap = new HashMap<Rating, Double>();
        // do i need to specify in the second curly brackets?
        //items in the hashmap aren't in the order??

        for(Rating rating: allRatingsList)
        {
            allRatingsHashMap.put(rating, averageRating(rating.getRatedMovieID()));
        }

        //we sort a hashmap
        allRatingsHashMap = sortHashMapByValue(allRatingsHashMap);

        //now we need to send data back to a user. here return to the program
        //modify the method so that we can use it elsewhere

    }

    private HashMap<Rating, Double> sortHashMapByValue(HashMap<Rating, Double> hashMap)
    {
        //create a list of elements from elements of HashMap
        List<Map.Entry<Rating, Double>> list =
                new LinkedList<Map.Entry<Rating, Double> >(hashMap.entrySet());

        //sort the list
        Collections.sort(list, new Comparator<Map.Entry<Rating, Double>>() {
            @Override
            public int compare(Map.Entry<Rating, Double> o1, Map.Entry<Rating, Double> o2) {
                return (o1.getValue().compareTo(o2.getValue()));
            }
        });

        //put data from sorted list to a hashmap
        HashMap<Rating, Double> temp = new LinkedHashMap<Rating, Double>();
        for (Map.Entry<Rating, Double> element: list
             ) {
            temp.put(element.getKey(), element.getValue());
        }
        return temp;
    }



}
