package DAL.database;

import BE.Movie;
import DAL.IMovieDataAccess;
import DAL.exception.MrsDalException;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO_DB implements IMovieDataAccess {
    private DatabaseConnector databaseConnector;

    public MovieDAO_DB() throws IOException {
        databaseConnector = new DatabaseConnector();
    }


    public List<Movie> getAllMovies() {
        ArrayList<Movie> allMovies = new ArrayList<>();
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "SELECT * FROM Movie;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                Movie mov = new Movie(id, title, year);
                allMovies.add(mov);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return allMovies;
    }

    @Override
    public List<Movie> searchForTheMovies(String query) {
        try (Connection connection = databaseConnector.getConnection()) {
            List<Movie> resultMovies = new ArrayList<>();
            String sql = "SELECT * FROM Movie WHERE Title LIKE ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + query + "%");
            if (preparedStatement.execute()) {
                ResultSet resultSet = preparedStatement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    String title = resultSet.getString("Title");
                    int year = resultSet.getInt("Year");
                    Movie movie = new Movie(id, title, year);
                    resultMovies.add(movie);
                }
                return resultMovies;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null; // is something goes wrong
    }

    @Override
    public void add(Movie movie) {
        try (Connection con = databaseConnector.getConnection();) {
            String sqlCommand = "INSERT INTO Movies(id, title, yearOfRelease)" +
                    "VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sqlCommand);
            preparedStatement.setInt(1, movie.getId());
            preparedStatement.setString(2, movie.getTitle());
            preparedStatement.setInt(3, movie.getYear());

            int row = preparedStatement.executeUpdate();


        } catch (SQLServerException throwables) {
            throwables.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Movie movie) {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "UPDATE Movies SET title=?, yearOfRelease=? WHERE id=?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setInt(2, movie.getYear());
            preparedStatement.setInt(3, movie.getId());

            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not delete Movie: " + movie.toString());
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Movie movie) {
        try (Connection con = databaseConnector.getConnection()) {
            String sql = "DELETE FROM Movies WHERE id=?;";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, movie.getId());
            if (preparedStatement.executeUpdate() != 1) {
                throw new Exception("Could not delete Movie: " + movie.toString());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //I don't know when its useful
        @Override
    public Movie createMovie(int releaseYear, String title, Movie movie) {
        return null;
    }
///
}
