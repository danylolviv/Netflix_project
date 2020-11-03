package be;

public class Review {
    // should the id from movie be retrieved through accessing the movie name (by a search box ) so this property is the
    //object of movie, and the same goes for user Id

    //Movie movieId;
    //User userId;
    int userId;
    String movieTitle;
    int score;

    public Review(int uId, String movieTitle){
        this.userId = uId;
        this.movieTitle = movieTitle;
    }

    public void setScore(int score){
        if (score >= 0 && score <=5 ){
            this.score = score;
        }
        }

    public int getScore(){

        return score;
    }
}




