package GUI.Controller;


import GUI.Model.MovieModel;
import GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ListView moviesList,ratingList, userList;

    @FXML
    private Button searchButton, createButton, updateButton, deleteButton, enterButton;

   @FXML
   private TextField typeField;


    private MovieModel movieModel;
    private UserModel userModel;

    public Controller() {
       movieModel= new MovieModel();
       userModel = new UserModel();
    }

    public void searchAllMovies(ActionEvent event) {
        String text = typeField.getText();
       if(text != null)
       {
            //call a method from

          moviesList.getItems().setAll(movieModel.getFoundMovies(text));
       }
       else{
           moviesList.getItems().add("there is no input");
       }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //here we use movieModel
        moviesList.setItems(movieModel.getObservableMovies());

        userList.setItems(userModel.getObservableUsers());
    }



    public void UpdateMovie(ActionEvent event) {
    }

    public void DeleteMovie(ActionEvent event) {
    }

    public void Enter(ActionEvent event) {
    }
    @FXML
    private void CreateMovieWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("GUI/View/createWindow.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add new movies");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
