package DAL;

import BE.User;

import java.util.List;

public interface IUserDataAccess {
    List<User> getAllUsers();
}
