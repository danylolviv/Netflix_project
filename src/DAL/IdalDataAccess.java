package DAL;

import BE.Movie;
import BE.Rating;
import BE.User;
import DAL.exception.MrsDalException;

import java.io.IOException;
import java.util.List;

public interface IdalDataAccess {
    //operations on Movies
    List<Movie> getAllMovies() throws IOException;
    List<Movie> searchForTheMovies(String text);

    void add(Movie movie);

    void update(Movie movie) throws MrsDalException;

    void delete(Movie movie) throws MrsDalException;

    // Movie getMovieByID(int movieID);

    Movie createMovie(int releaseYear, String title, Movie movie) ;

    //operations on ratings
    List<Rating> getAllRatings();

    //operations on users
    List<User> getAllUsers();

}
