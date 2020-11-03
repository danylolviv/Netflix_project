package be;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    String title;
    List<String> cast = new ArrayList<>();

    public Movie(String title, String cast){
        this.title = title;
        this.cast.add(cast);
    }

    public String getMovieTtle(){
        return title;
    }

    public List showReviews(String title){
        if (title == this.title){
            return cast;
        }
            return null;
    }
}
