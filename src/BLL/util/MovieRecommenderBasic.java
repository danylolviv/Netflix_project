package BLL.util;


import BE.Movie;
import BE.Rating;
import DAL.DALcontroller;

import java.util.ArrayList;
import java.util.List;

/**
 * Return movies in the order regarding the Best rated movies and the movies that were not rated by this user
 */
public class MovieRecommenderBasic {
    private DALcontroller daLcontroller;

    public MovieRecommenderBasic()
    {
        daLcontroller = new DALcontroller();
    }

  public List<Movie> getMoviesWithRatings()
    {

        List<Movie> Allmovies = daLcontroller.getAllMovies();
        List<Rating> Allratings = daLcontroller.getAllRatings();

        //for each movie we add ratings
        for(Movie movie : Allmovies) // m
            for(Rating rating: Allratings) // n
            {
                if(movie.getId()==rating.getRatedMovieID()) {
                   // movie.getRatings().add(rating);
                    movie.addRating(rating);
                    //I don't add to users beacuse i dont see a need now
                }
            }

        Allmovies.sort(((o1, o2) -> (int) ((int) o2.getAverageRating()-o1.getAverageRating())));

          return Allmovies;



    }


}
