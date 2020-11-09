package BLL;

import BE.Movie;
import DAL.IMovieDataAccess;
import DAL.MovieDAO;

import java.io.IOException;
import java.util.List;

public class MovieManager {
    private IMovieDataAccess movieDAO;

    public MovieManager()
    {
        movieDAO = new MovieDAO();
    }
    public List<Movie> getAllMovies() throws IOException {return movieDAO.getAllMovies();}

}
