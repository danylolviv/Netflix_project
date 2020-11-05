package GUI;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private MovieModel movieModel;

    public Controller(MovieModel movieModel) {
        this.movieModel = movieModel;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
