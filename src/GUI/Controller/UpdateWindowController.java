package GUI.Controller;

import BE.Movie;
import GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

public class UpdateWindowController {
    private MovieModel movieModel;
    private Controller controller;

    @FXML
    private TextField IDfield, titleField, yearField;

    public void setModel(MovieModel movieModel) {
        this.movieModel = movieModel;
        controller = new Controller();
    }

    /**
     * when we press save
     * @param event
     */
    public void updateMovie(ActionEvent event) {
        // here we update movie which is sent from the controller
        Movie movie = controller.sendSelectedMovie();
        movie.setTitle(titleField.getText());
        movie.setYear(Integer.parseInt(yearField.getText()));
        //then we need to send it back
        movieModel.updateMovie(movie);

    }
    public Movie sendTheMovieBack(Movie movie)
    {
        return movie;
    }

    /**
     * when we press cancel. Do nothing and close the window
     * @param event
     */
    public void closeTheWindow(ActionEvent event) {
    }
}
