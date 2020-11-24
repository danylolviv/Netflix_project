package BLL;

import BE.Movie;
import DAL.DALcontroller;
import DAL.IMovieDataAccess;
import DAL.file.MovieDAO;
import DAL.exception.MrsDalException;

import java.io.IOException;
import java.util.List;

public class MovieManager {
        private DALcontroller daLcontroller;


    public MovieManager()
    {
        daLcontroller = new DALcontroller();

    }
    //MovieDAO operations
    public List<Movie> getAllMovies() throws IOException {return daLcontroller.getAllMovies();}
    public List<Movie> searchForTheMovies(String text) { return daLcontroller.searchForTheMovies(text);}


    public void add(Movie movie) {
        daLcontroller.add(movie);
    }

    public void updateMovie(Movie movie) {
        try {
            daLcontroller.update(movie);
        } catch (MrsDalException e) {
            e.printStackTrace();
        }
    }

    public void delete(Movie movie) throws MrsDalException {
        daLcontroller.delete(movie);
    }
}
