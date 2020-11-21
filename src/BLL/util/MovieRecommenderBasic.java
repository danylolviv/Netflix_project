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
            ratingDAO= new RatingDAO();
        }


    private double averageRating(int ratedMovieID)
    {
      //  Movie movie = movieDAO.getMovieByID(ratedMovieID);

        List<Rating> allRatings = ratingDAO.getAllRatings();
        double sumRating=0.0;
        int iterations=0;

        for(Rating rating: allRatings)
        {
           // if(movieDAO.getMovieByID(ratedMovieID).getId()==rating.getRatedMovieID())
            if(ratedMovieID==rating.getRatedMovieID())
            {
                sumRating+= rating.getRating();
                iterations++;
            }
        }
        return sumRating/iterations;
    }

    /**
     * Calculate the ratings
     * add them to the HashMap
     * sort
     * return hashmap with ratings in the descending order
     */
    private HashMap<Rating, Double> getSortedRatings()
    {
        List<Rating> allRatingsList = ratingDAO.getAllRatings();
        HashMap<Rating, Double> allRatingsHashMap = new HashMap<>(); // i removed from the curly brackets
        // do i need to specify in the second curly brackets?
        //items in the hashmap aren't in the order??

        for(Rating rating: allRatingsList)
        {
            allRatingsHashMap.put(rating, averageRating(rating.getRatedMovieID()));
        }

        //we sort a hashmap
        allRatingsHashMap = sortHashMapByValue(allRatingsHashMap);

        //send the sorted hashmap
        return allRatingsHashMap;
    }

    /**
     * this method will be used in the GUI layer
     * @return
     */
    public List<Movie> getRecommendedMovies()
    {
        List<Movie> moviesToRecommend = new ArrayList<>();
        HashMap<Rating, Double> sortedRecommendations = getSortedRatings();

        // for each rating find corresponding movie and add it to the list
        // for each not appliable to HashMap. need to create a list
        List<Map.Entry<Rating, Double>> list =

               new LinkedList< >(sortedRecommendations.entrySet());//Map.Entry<Rating, Double>

        for (Map.Entry<Rating, Double> element: list
             ) {
            moviesToRecommend.add(movieDAO.getMovieByID(element.getKey().getRatedMovieID()));
        }
        return moviesToRecommend;
    }

    /**
     * sort the hashMap by value
     * @param hashMap
     * @return
     */
    private HashMap<Rating, Double> sortHashMapByValue(HashMap<Rating, Double> hashMap)
    {
        //create a list of elements from elements of HashMap
        List<Map.Entry<Rating, Double>> list =
                new LinkedList< >(hashMap.entrySet());

        //sort the list
        Collections.sort(list, new Comparator<Map.Entry<Rating, Double>>() {
            @Override
            public int compare(Map.Entry<Rating, Double> o1, Map.Entry<Rating, Double> o2) { // here there may be problem with reference type
                //sort in the descending order
                return (o2.getValue().compareTo(o1.getValue()));
            }
        });

        //put data from sorted list to a hashmap
        HashMap<Rating, Double> temp = new LinkedHashMap<>();
        for (Map.Entry<Rating, Double> element: list
             ) {
            temp.put(element.getKey(), element.getValue());
        }
        return temp;
    }



}
