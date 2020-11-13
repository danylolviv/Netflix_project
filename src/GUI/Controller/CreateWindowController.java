package GUI.Controller;

import BE.Movie;
import GUI.Model.MovieModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class CreateWindowController implements Initializable {
    @FXML
    private TextField IDfield, titleField, yearField;

    @FXML
    private TableColumn<Movie, Integer> IDcolumn;
    @FXML
    private TableColumn<Movie, Integer> yearColumn;

    @FXML
    private TableColumn<Movie, String> titleColumn;

    @FXML
    private TableView<Movie> movieTable;

    private MovieModel movieModel ;
    /**
     * If three TextFilelds are != empty add a movie
     * @param event
     */
    public void addMovieButton(ActionEvent event) {

        Movie movie = new Movie(Integer.parseInt(IDfield.getText()),
                titleField.getText(), Integer.parseInt(yearField.getText()));
        movieModel.addMovie(movie);

        IDfield.clear();
        yearField.clear();
        titleField.clear();
    }



    public void loadAllButton(ActionEvent event) {
       movieModel.loadMovies();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        IDcolumn.setCellValueFactory( new PropertyValueFactory<>("id"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        titleColumn.setCellValueFactory(cell-> new ReadOnlyObjectWrapper(cell.getValue().getTitle()));

    }

    public void setModel(MovieModel movieModel) {
        this.movieModel = movieModel;
        movieTable.setItems(movieModel.getObservableMovies()); // testing needed
    }
}
