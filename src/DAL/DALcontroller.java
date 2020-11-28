package DAL;

import BE.Movie;
import BE.Rating;
import BE.User;
import DAL.exception.MrsDalException;
import DAL.file.MovieDAO;
import DAL.file.RatingDAO;
import DAL.file.UserDAO;

import java.io.IOException;
import java.util.List;

public class DALcontroller implements IdalDataAccess{
    private IMovieDataAccess movieAccess;
    private  IUserDataAccess userAccess;
    private  IRatingDataAccess ratingAccess;

    public DALcontroller() {
        movieAccess= new MovieDAO();
        userAccess = new UserDAO();
        ratingAccess = new RatingDAO();
    }

    @Override
    public List<Movie> getAllMovies()  {
        return movieAccess.getAllMovies();
    }

    @Override
    public List<Movie> searchForTheMovies(String text) {
        return movieAccess.searchForTheMovies(text);
    }

    @Override
    public void add(Movie movie) {
        movieAccess.add(movie);

    }

    @Override
    public void update(Movie movie) throws MrsDalException {
        movieAccess.update(movie);

    }

    @Override
    public void delete(Movie movie) throws MrsDalException {
        movieAccess.delete(movie);
    }

    @Override
    public Movie createMovie(int releaseYear, String title, Movie movie) {
       return movieAccess.createMovie(releaseYear, title, movie);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingAccess.getAllRatings();
    }

    @Override
    public List<User> getAllUsers() {
        return userAccess.getAllUsers();
    }
}
