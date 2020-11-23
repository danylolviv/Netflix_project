package DAL.database;

import BE.Movie;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO_DB {
    private DatabaseConnector databaseConnector;

    public MovieDAO_DB() throws IOException {
        databaseConnector= new DatabaseConnector();
    }

    public List<Movie> getAllMovies()
    {
        ArrayList<Movie> allMovies = new ArrayList<>();
        try (Connection con = databaseConnector.getConnection())
        {
            String sql = "SELECT * FROM Movie;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int year = rs.getInt("year");
                Movie mov = new Movie(id, title, year);
                allMovies.add(mov);
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return allMovies;
    }

}
