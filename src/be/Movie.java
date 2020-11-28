package BE;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private  int id;
    private String title;
    private int year;
    private List<Rating> ratings;

    public Movie(int id, String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.ratings = new ArrayList<>();
    }



    public double getAverageRating()  {

        double sumRating=0.0;

        for(Rating r : ratings)
            sumRating+= r.getRating();

        return (sumRating/ratings.size());
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
    public void addRating(Rating rating)
    {
        this.ratings.add(rating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        return id == movie.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "ID:" + id +" title: " + title + " year: "+ year;
    }
}
