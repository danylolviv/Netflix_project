package GUI.Controller;

import BE.Movie;
import GUI.Model.MovieModel;
import javafx.beans.property.ReadOnlyObjectWrapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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
     * If two TextFilelds are != empty add a movie
     * @param event
     */
    public void addMovieButton(ActionEvent event) {

        if(IDfield!=null&& yearField!=null){
        Movie movie = new Movie(Integer.parseInt(IDfield.getText()),
                titleField.getText(), Integer.parseInt(yearField.getText()));
        movieModel.addMovie(movie);

        IDfield.clear();
        yearField.clear();
        titleField.clear();
        }
        else{
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/GUI/View/createWindowError.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle("Error");
            stage.setScene(new Scene(root));
            stage.show();
        }
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
        movieTable.setItems(movieModel.getObservableMovies());
    }
}
