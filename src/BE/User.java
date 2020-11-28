package BE;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int ID;
    private String name;
    private List<Movie> ratedMovies;

    public User(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.ratedMovies = new ArrayList<>();
    }

    public void addRatedMovie(Movie movie)
    {
        ratedMovies.add(movie);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ID: " + ID +" Name: "+ name;
    }
}
