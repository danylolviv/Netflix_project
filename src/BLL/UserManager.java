package BLL;

import BE.User;
import DAL.file.UserDAO;

import java.io.IOException;
import java.util.List;

public class UserManager {
    private UserDAO userDAO;

    public UserManager() {
       userDAO = new UserDAO();
    }
    //UserDAO operations
    public List<User> getAllUsers() throws IOException {return userDAO.getAllUsers();}
}
