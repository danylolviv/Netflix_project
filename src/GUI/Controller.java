package GUI;


import DAL.MovieDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ListView moviesList;

    @FXML
    private Button searchButton;


    private MovieModel movieModel;

    public Controller() {
       movieModel= new MovieModel();
    }

    public void searchAllMovies(ActionEvent event) {
        moviesList.getItems().add("test");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moviesList.setItems(movieModel.getObservableMovies());
    }
}
