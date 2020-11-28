package DAL;

import BE.Movie;
import DAL.exception.MrsDalException;

import java.io.IOException;
import java.util.List;

public interface IMovieDataAccess {
    List<Movie> getAllMovies() ;
   List<Movie> searchForTheMovies(String text);

    void add(Movie movie);

    void update(Movie movie) throws MrsDalException;

    void delete(Movie movie) throws MrsDalException;

    // Movie getMovieByID(int movieID);

     Movie createMovie(int releaseYear, String title, Movie movie) ;



}
