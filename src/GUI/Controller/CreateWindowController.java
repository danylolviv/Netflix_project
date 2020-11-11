package GUI.Controller;

import BE.Movie;
import GUI.Model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class CreateWindowController {
    @FXML
    private TextField IDfield, titleField, yearField;

    @FXML
    private Button addButton, saveButton, loadAllButton;

    @FXML
    private javafx.scene.control.TableColumn<Movie, Integer> IDcolumn;
    @FXML
    private javafx.scene.control.TableColumn<Movie, Integer> yearColumn;

    @FXML
    private TableColumn tileColumn;

    @FXML
    private TableView<Movie> movieTable;

    private MovieModel movieModel;
    /**
     * If three TextFilelds are != empty add a movie
     * @param event
     */
    public void addMovieButton(ActionEvent event) {
        movieModel.getObservableMovies().add( new Movie(Integer.parseInt(IDfield.getText()),
                titleField.getText(), Integer.parseInt(yearField.getText())));

        IDfield.clear();
        yearField.clear();
        titleField.clear();
    }

    public void saveMoviesButton(ActionEvent event) {
        movieModel.getObservableMovies().addAll(movieTable.getItems());
    }

    public void loadAllButton(ActionEvent event) {
       movieTable.setItems(movieModel.getObservableMovies()); // testing needed
    }
}
