package BLL;

import BE.Movie;
import BE.User;
import DAL.file.RatingDAO;

public class RatingManager {
    private RatingDAO ratingDAO;

    public RatingManager()
    {
        ratingDAO = new RatingDAO();
    }

    public void rate(Object selectedRating, Movie movie, User selectedUser) {
        ratingDAO.rate(selectedRating, movie, selectedUser);
    }
}
