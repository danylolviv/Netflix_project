package GUI.Model;

import BE.Movie;
import BLL.MovieManager;
import BLL.util.MovieRecommenderBasic;
import DAL.exception.MrsDalException;
import GUI.Controller.Controller;
import GUI.Controller.UpdateWindowController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MovieModel implements Initializable {
    private ObservableList moviesToBeViewed;
    private MovieManager movieManager;
    private Controller controller;
    private MovieRecommenderBasic movieRecommenderBasic;

    private ObservableList moviesToBeRecommended;

    public MovieModel(){
        movieRecommenderBasic = new MovieRecommenderBasic();
        movieManager = new MovieManager();
        moviesToBeViewed = FXCollections.observableArrayList();

        moviesToBeRecommended=FXCollections.observableArrayList();

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
    public void update(Movie movie)
    {
        movieManager.updateMovie(movie);
    }


    public void searchAllMovies(String text) {
        try{
            moviesToBeViewed.removeAll(getObservableMovies());
            moviesToBeViewed.addAll(movieManager.searchForTheMovies(text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void deleteMovie(Movie movie) throws MrsDalException {
        movieManager.delete(movie);
    }

    public ObservableList<Movie> getRecommendedMovies() {
       return moviesToBeRecommended;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moviesToBeRecommended.addAll(movieRecommenderBasic.getRecommendedMovies());
    }
}
