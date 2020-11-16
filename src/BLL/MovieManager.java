package BLL;

import BE.Movie;
import BE.User;
import DAL.IMovieDataAccess;
import DAL.MovieDAO;
import DAL.UserDAO;
import DAL.exception.MrsDalException;

import java.io.IOException;
import java.util.List;

public class MovieManager {
    private IMovieDataAccess movieDAO;


    public MovieManager()
    {
        movieDAO = new MovieDAO();
    }
    //MovieDAO operations
    public List<Movie> getAllMovies() throws IOException {return movieDAO.getAllMovies();}
    public List<Movie> searchForTheMovies(String text) { return movieDAO.searchForTheMovies(text);}


    public void add(Movie movie) {
        movieDAO.add(movie);
    }

    public void updateMovie(Movie movie) {
        try {
            movieDAO.update(movie);
        } catch (MrsDalException e) {
            e.printStackTrace();
        }
    }

    public void delete(Movie movie) throws MrsDalException {
        movieDAO.delete(movie);
    }
}
