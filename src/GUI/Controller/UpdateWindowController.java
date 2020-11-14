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
        Movie movie = new Movie(controller.sendIDofTheSelectedMovie(), titleField.getText(),
                Integer.parseInt(yearField.getText()));
        movieModel.update(movie);
    }



    public void closeTheWindow(ActionEvent event) {
    }
}
