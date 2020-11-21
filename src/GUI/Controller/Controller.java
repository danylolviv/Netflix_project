package GUI.Controller;


import BE.Movie;
import BE.Rating;
import BE.User;
import BLL.util.MovieRecommenderBasic;
import GUI.Model.MovieModel;
import GUI.Model.RatingModel;
import GUI.Model.UserModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private User selectedUser;
    //TableView movie
    @FXML
   private TableView<Movie> tableMovie;
    @FXML
    private TableColumn<Movie, Integer> columnId;
    @FXML
    private TableColumn<Movie, Integer> columnYear;
    @FXML
    private TableColumn<Movie, String> columnTitle;

    //TableView for users
    @FXML
    private TableView<User> usersTable;
    @FXML
    private  TableColumn<User, String>  usersColumn;

    //searching field
   @FXML
   private TextField typeField;

   //information about a current user
    @FXML
    private Text currentUserField;

    //combobox for rating
    @FXML
    private  ComboBox ratingComboBox;

    //TableView for recommended Movies
    @FXML
    private  TableView<Movie> recommendedMoviesTable;


    private MovieModel movieModel;
    private UserModel userModel;
    private RatingModel ratingModel;
    private MovieRecommenderBasic movieRecommendationBasic;

    public Controller() {
       movieModel= new MovieModel();
       userModel = new UserModel();
       ratingModel = new RatingModel();
        movieRecommendationBasic =new MovieRecommenderBasic();
    }

    public void searchAllMovies(ActionEvent event) {
        String text = typeField.getText();
        movieModel.searchAllMovies(text);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnId.setCellValueFactory( new PropertyValueFactory<Movie, Integer>("id"));
        columnYear.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year"));
        //columnTitle.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
       columnTitle.setCellValueFactory(cell-> new ReadOnlyObjectWrapper(cell.getValue().getTitle()));

       usersColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
       usersTable.setItems(userModel.getObservableUsers());
       setItemsComboBox();
    }


    private void setItemsComboBox()
    {

       Integer[] ratingOptions = {-5, -4, -3, -2, -1, 1, 2, 3, 4, 5};
        ObservableList<Integer> observableList = FXCollections.observableArrayList();
        observableList.addAll(Arrays.asList(ratingOptions));

        ratingComboBox.setItems(observableList);


    }

    public void setModel(MovieModel movieModel) {
        this.movieModel = movieModel;
        tableMovie.setItems(movieModel.getObservableMovies());
    }

    public void UpdateMovie(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("/GUI/View/updateWindow.fxml"));
        Parent root = loader.load();

        UpdateWindowController updateWindowController = loader.getController();
        updateWindowController.
                setModel(movieModel, tableMovie.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        stage.setTitle("Update");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public int sendIDofTheSelectedMovie()
    {
        return tableMovie.getSelectionModel().getSelectedItem().getId();
    }

    public void DeleteMovie(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("/GUI/View/deleteMovie.fxml"));
        Parent root = loader.load();

        DeleteMovieController deleteMovieController = loader.getController();
        deleteMovieController.
                setModel(movieModel, tableMovie.getSelectionModel().getSelectedItem());

        Stage stage = new Stage();
        stage.setTitle("Update");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void LoadAll(ActionEvent event) {
        movieModel.loadMovies();
        tableMovie.setItems(movieModel.getObservableMovies());
    }
    @FXML
    private void CreateMovieWindow(ActionEvent event)  {
        //loader insteada of static instance of controller
        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("/GUI/View/createWindow.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // get the instance of the controller of the FXML loader
        CreateWindowController createWindowController = loader.getController();
        createWindowController.setModel(movieModel);
        Stage stage = new Stage();
        stage.setTitle("Add new movies");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * when the user logs in recommend movies for him
     * @param event
     */
    public void selectUser(ActionEvent event) {
        selectedUser = usersTable.getSelectionModel().getSelectedItem();
        currentUserField.setText("Current User: " +
                usersTable.getSelectionModel().getSelectedItem().getName());
        //functionality doesn't work in the very weird way
      // recommendedMoviesTable.setItems((ObservableList<Movie>) movieRecommendationBasic.getRecommendedMovies());
        recommendedMoviesTable.setItems(movieModel.getRecommendedMovies());
    }

    public void rate(ActionEvent event) {
        ratingModel.rate(ratingComboBox.getSelectionModel().getSelectedItem(),
                tableMovie.getSelectionModel().getSelectedItem(), selectedUser);
    }
}
