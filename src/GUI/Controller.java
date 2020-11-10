package GUI;


import DAL.MovieDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


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

    public Controller() {
       movieModel= new MovieModel();
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
    }
}
