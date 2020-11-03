package sample;

import be.Movie;
import be.Review;
import be.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        // primaryStage.setTitle("Hello World");
        // primaryStage.setScene(new Scene(root, 300, 275));
        // primaryStage.show();

        User u1 = new User(1, "Bill", 23);
        Movie m1 = new Movie("Tenet", "Robert Patison");
        Review r1 = new Review(u1.getUserId(),m1.getMovieTtle());
        r1.setScore(5);
        System.out.println(r1.getScore());


    }


    public static void main(String[] args) {
        launch(args);
    }
}
