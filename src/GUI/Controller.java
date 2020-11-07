package GUI;


import DAL.MovieDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.io.IOException;

public class Controller {
    @FXML
    private ListView moviesList;

    @FXML
    private Button searchButton;

    private MovieDAO movieDAO;


    public void searchAllMovies(ActionEvent event) {
        moviesList.getItems().add("test");
    }
}
