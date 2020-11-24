package BLL.util;

import BE.Movie;
import BE.Rating;
import DAL.file.MovieDAO;
import DAL.file.RatingDAO;
import DAL.file.UserDAO;

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

    /**
     * Calculate an average rating for a given movie
     * @param //ratedMovieID
     * @return
     */
    /*
    private double averageRating(int ratedMovieID) throws IOException {
      //  Movie movie = movieDAO.getMovieByID(ratedMovieID);

        //List<Rating> allRatings = ratingDAO.getAllRatings();
        double sumRating=0.0;
        int iterations=0;

        for(Rating rating: ratingDAO.getAllRatings()) // n
        {
           // if(movieDAO.getMovieByID(ratedMovieID).getId()==rating.getRatedMovieID())
            if(ratedMovieID==rating.getRatedMovieID())
            {
                sumRating+= rating.getRating();
                iterations++;
            }
        }
        return (sumRating/iterations);
    }*/
    private double getAverageRating(Movie m)  {

        double sumRating=0.0;
        List<Rating> ratings = m.getRatings();

        for(Rating r : ratings)
            sumRating+= r.getRating();

        return (sumRating/ratings.size());
    }
    private List<Movie> getMoviesWithRatings()
    {
        List<Movie> movies = movieDAO.getAllMovies();
        List<Rating> ratings = ratingDAO.getAllRatings();

        for(Movie movie : movies) // m
        for(Rating rating: ratings) // n
        {
            if(movie.getId()==rating.getRatedMovieID()) {
                movie.getRatings().add(rating);
            }
        }
        return movies;
    }

    /**
     * Calculate the ratings
     * add them to the HashMap
     * sort
     * return hashmap with ratings in the descending order
     */
    public List<Movie> getRecommendedMovies()
    {
        List<Movie> movies = getMoviesWithRatings();
        movies.sort((o1, o2) -> (int) (getAverageRating(o1)-getAverageRating(o2)));
        return movies;
    }
    /*private LinkedHashMap<Rating, Double> getSortedRatings() // 2*n*n
    {
        List<Rating> allRatingsList = null;
        try {
            allRatingsList = ratingDAO.getAllRatings();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinkedHashMap<Rating, Double> allRatingsHashMap = new LinkedHashMap<>(); // i removed from the curly brackets
        // do i need to specify in the second curly brackets?
        //items in the hashmap aren't in the order??

        for(Rating rating: allRatingsList)
        {
            try {
                allRatingsHashMap.put(rating, averageRating(rating.getRatedMovieID()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //we sort a hashmap
        allRatingsHashMap = sortHashMapByValue(allRatingsHashMap);

        //send the sorted hashmap
        return allRatingsHashMap;
    }*/

    /**
     * this method will be used in the GUI layer
     * @return
     */
    /*public List<Movie> getRecommendedMovies()
    {
        List<Movie> moviesToRecommend = new ArrayList<>();
        LinkedHashMap<Rating, Double> sortedRatings = getSortedRatings(); // 2n*n

        // for each rating find corresponding movie and add it to the list
        // for each not appliable to HashMap. need to create a list
        List<Map.Entry<Rating, Double>> list =
                new LinkedList< >(sortedRatings.entrySet());//Map.Entry<Rating, Double>

        for (Map.Entry<Rating, Double> element: list // n
             ) {
            moviesToRecommend.add(movieDAO.getMovieByID(element.getKey().getRatedMovieID())); // n * m*time in file
        }
        return moviesToRecommend;
    }*/


    /**
     * sort the hashMap by value
     * @param hashMap
     * @return
     */
    private LinkedHashMap<Rating, Double> sortHashMapByValue(LinkedHashMap<Rating, Double> hashMap)
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
        LinkedHashMap<Rating, Double> temp = new LinkedHashMap<>();
        for (Map.Entry<Rating, Double> element: list
             ) {
            temp.put(element.getKey(), element.getValue());
        }
        return temp;
    }



}
