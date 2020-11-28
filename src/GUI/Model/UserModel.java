package GUI.Model;

import BE.User;
import BLL.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class UserModel {
    private ObservableList usersToBeViewed;
    private UserManager userManager;

    public UserModel(){
        userManager = new UserManager();
        usersToBeViewed = FXCollections.observableArrayList();
        try {
            usersToBeViewed.addAll(userManager.getAllUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public ObservableList<User> getObservableUsers(){return usersToBeViewed;}
}
