package GUI.Model;

import BE.Movie;
import BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class MovieModel {
    private ObservableList moviesToBeViewed;
    private MovieManager movieManager;


    public MovieModel(){
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();

    }
    public void loadMovies()
    {
        try {
            moviesToBeViewed.addAll(movieManager.getAllMovies());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Movie> getObservableMovies(){
        return moviesToBeViewed;
    }
    public List<Movie> getFoundMovies(String text){ return movieManager.searchForTheMovies(text);}

    public void addMovie(Movie movie)
    {
        moviesToBeViewed.add(movie);
        movieManager.add(movie);
    }
}
