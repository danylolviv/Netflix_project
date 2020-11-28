package DAL.database;

import BE.Movie;
import BE.Rating;
import BE.User;
import DAL.file.MovieDAO;
import DAL.file.RatingDAO;
import DAL.file.UserDAO;
import com.microsoft.sqlserver.jdbc.SQLServerException;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddFilesToDB {
    private DatabaseConnector databaseConnector;
    private MovieDAO movieDAO;
    private RatingDAO ratingDAO;
    private UserDAO userDAO;

    public AddFilesToDB() throws IOException {
        databaseConnector = new DatabaseConnector();
        movieDAO = new MovieDAO();
        ratingDAO = new RatingDAO();
        userDAO = new UserDAO();
    }

    public void addMoviesToDB() {
        List<Movie> allMovies = movieDAO.getAllMovies();
        int rowsAffected;
        try (Connection con = databaseConnector.getConnection();) {
           String sqlCommand = "INSERT INTO Movies ( title, yearOfRelease) VALUES( ?, ?);";
            PreparedStatement preparedStatement = con.prepareStatement(sqlCommand) ;
                for (Movie movie : allMovies) {
                    //preparedStatement.setInt(1, movie.getId());
                    preparedStatement.setString(1, movie.getTitle());
                    preparedStatement.setInt(2, movie.getYear());

               // ResultSet resultSet = preparedStatement.executeQuery(sqlCommand);
                    int row = preparedStatement.executeUpdate();

                }
            } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void addRatingsToDB()
    {
        List<Rating> allRatings = ratingDAO.getAllRatings();
        try (Connection con = databaseConnector.getConnection();)
        {
            String sqlCommand = "INSERT INTO Ratings (ratedMovieID, userID, rating) " +
                    "VALUES( ?, ?, ?) ;";

            PreparedStatement preparedStatement = con.prepareStatement(sqlCommand);
            for(Rating rating: allRatings)
            {
                if(rating!=null && rating.getRatedMovieID()!=-1 &&
                        rating.getRating()!=-1 && rating.getUserID()!=-1) {
                    preparedStatement.setInt(1, rating.getRatedMovieID());
                    preparedStatement.setInt(2, rating.getUserID());
                    preparedStatement.setInt(3, rating.getRating());
                }

                int row = preparedStatement.executeUpdate();
            }

        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addUsersToDB()
    {
        List<User> allUsers = userDAO.getAllUsers();

        try(Connection con = databaseConnector.getConnection();) {
            String sqlCommand = "INSERT INTO Users( id, name) VALUES(?, ?);";

            PreparedStatement preparedStatement = con.prepareStatement(sqlCommand);
            for(User user: allUsers)
            {
                    preparedStatement.setInt(1, user.getID());
                    preparedStatement.setString(2, user.getName());
                    int row = preparedStatement.executeUpdate();
                }



            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }




