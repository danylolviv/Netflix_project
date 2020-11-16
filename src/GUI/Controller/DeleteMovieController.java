package GUI.Controller;

import BE.Movie;
import DAL.exception.MrsDalException;
import GUI.Model.MovieModel;
import javafx.event.ActionEvent;

public class DeleteMovieController {
    private MovieModel movieModel;
    private Movie movie;

    /**
     * method calls another method and closes the window
     * @param event
     */
    public void deleteMovie(ActionEvent event) {
        try {
            movieModel.deleteMovie(movie);
        } catch (MrsDalException e) {
            e.printStackTrace();
        }
        closeTheWindow();
    }

    /**
     * method just closes the window
     * @param event
     */
    public void closeTheWindow(ActionEvent event) {
        closeTheWindow();
    }

    /**
     * Method wrote to avoid repetition
     */
    private void closeTheWindow()
    {

    }
    public void setModel(MovieModel movieModel, Movie selectedItem) {
        this.movieModel = movieModel;
        this.movie = selectedItem;
    }
}
