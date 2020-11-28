package BE;

public class Rating {
    private int userID=-1;
    private int ratedMovieID=-1;
    //rating should be in the scale from -5 to 5
    private int rating=-1;

    public Rating(int userID, int ratedMovieID, int rating) {
        this.userID = userID;
        this.ratedMovieID = ratedMovieID;
        this.rating = rating;
    }



    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getRatedMovieID() {
        return ratedMovieID;
    }

    public void setRatedMovieID(int ratedMovieID) {
        this.ratedMovieID = ratedMovieID;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
