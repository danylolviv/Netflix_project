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


    private int ID=-1;
    private Controller controller;
    private MovieModel movieModel;
    private Movie movie;

    @FXML
    private TextField titleField, yearField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        movie.setId(controller.sendIDofTheSelectedMovie());

    }

    /**
     * We will send just an ID not an object
     * @param ID
     */
    public void receiveMovieIDFromController(Integer ID)
    {
        this.ID=ID;
    }

    public void updateMovie(ActionEvent event) {
        //controller.sendIDofTheSelectedMovie()
        Movie movie1 = new Movie(movie.getId(), titleField.getText(),
                Integer.parseInt(yearField.getText()));

        movieModel.update(movie1);
    }



    public void closeTheWindow(ActionEvent event) {
    }

    public void setModel(MovieModel movieModel, Movie movie) {
        this.movieModel=movieModel;
        this.movie=movie;
    }
}
