package BLL.util;

import BE.Movie;
import BE.Rating;
import BE.User;
import DAL.MovieDAO;
import DAL.RatingDAO;
import DAL.UserDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieRecommender {
    private MovieDAO movieDAO;
    private UserDAO userDAO;
    private RatingDAO ratingDAO;

    public MovieRecommender() {
        movieDAO = new MovieDAO();
        userDAO = new UserDAO();
    }

    /**
     * take all the ratings that a user gave
     * based on that ratings find all the people who rated this same movies as this user
     * calculate a vector for the person in question and all others consequently.
     * put them into an Array then sort it
     * @return
     */
    public List<Movie> recommendMovies()
    {

    }

    /**
     * look for that user ID in the ratings.txt
     * if it is there get the ID of the movie
     * find it in the movie_titles.txt and add to the list ratedMovies
     * return them in the alphabetical order
     * @param user
     * @return
     */
    public List<Movie> getMoviesRatedByUser(User user)
    {
        List<Movie> ratedMovies = new ArrayList<>();
        for (Movie m: movieDAO.getAllMovies()) {
            if(m.getId()== user.getID())
            {
                ratedMovies.add(m);
            }
        }
        //sort alphabetically
        Collections.sort(ratedMovies, (o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
        return ratedMovies;

    }

    /**
     * get a list of all ratings
     *we need to get all ratings from a RatingDAO
     * then search if the movie.getID is in the line
     * @param movie
     * @return
     */
    public List<User> getUsersWhoRated(Movie movie)
    {
        List<User> usersWhoRated = new ArrayList<>();
        List<Rating> ratings = ratingDAO.getAllRatings();
        for(Rating rate: ratings)
        {
            if(rate.getRatedMovieID() == movie.getId())
            {   //rate.getUserID()
                usersWhoRated.add();
            }
        }
    }

    /**
     * returns the ratings made by the user in the alphabetical order
     * concerning the titles of the movie
     * @return
     */
    public List<Rating> getRatingsFromMovies()
    {

    }

    public double calculateVector(List<Rating> ratingsUser, List<Rating> ratingsAnotherUser)
    {

    }




}
