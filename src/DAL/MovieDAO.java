package DAL;

import BE.Movie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements IMovieDataAccess {
    //CRUD operations
    // getAllMoviesMethod
    //Update a certain Movie
    // delate a certain movie

    private static final String MOVIE_SOURCE =
            "D:\\onedrive2\\OneDrive - Erhvervsakademi Sydvest\\" +
                    "week 45\\netfilx\\Netflix_project\\data\\movie_titles";

    public List<Movie> getAllMovies() throws IOException {
        List<Movie> movies = new ArrayList<>();

        BufferedReader br =  new BufferedReader(new FileReader(MOVIE_SOURCE));

        String contentLine;
        while((contentLine= br.readLine() )!= null)
        {
           movies.add(makeObjectFromString(contentLine));
        }
        return movies;
    }

    private Movie makeObjectFromString(String line)
    {
        String[] splittedLine = line.split(",");
        int id = Integer.parseInt(splittedLine[0]);
        int year = Integer.parseInt(splittedLine[1]);
        String title = splittedLine[2];

        Movie movie = new Movie(id, title, year);
        return movie;
    }

}



