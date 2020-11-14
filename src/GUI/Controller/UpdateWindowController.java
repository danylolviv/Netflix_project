package GUI.Controller;

import BE.Movie;
import GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateWindowController implements Initializable {
    private MovieModel movieModel;
    private Controller controller;
    private Movie movie;

    @FXML
    private TextField IDfield, titleField, yearField;

// remember to add this method to the movie
public UpdateWindowController(MovieModel movieModel)
{
   this.movieModel = movieModel;
}

    /**
     * when we press save
     * @param event
     */
    public void updateMovie(ActionEvent event) {
       movie.setTitle(titleField.getText());
       movie.setYear(Integer.parseInt(String.valueOf(yearField.getText())));
       //movie.getId(); // meybe there is something wrong with ID
        if(movie.getId()!=0) // just to check if its empty
            movieModel.updateMovie(movie);
        else
            System.out.println("movie is empty. Problem in the line 35 Updat. wind. contr");

    }

    /**
     * when we press cancel. Do nothing and close the window
     * @param event
     */
    public void closeTheWindow(ActionEvent event) {
    }


    public void changeTheMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void sendMovie(Movie movie) {
        this.movie = movie;

    }

    public void setModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }
}
