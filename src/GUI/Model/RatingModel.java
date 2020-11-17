package GUI.Model;

import BE.Movie;
import BE.User;
import BLL.RatingManager;

public class RatingModel {
    private RatingManager ratingManager;

    public RatingModel()
    {
        ratingManager = new RatingManager();
    }

    /**
     * method receives an object but its a Integer type so its int
     * @param selectedItem
     * @param item
     */
    public void rate(Object selectedRating, Movie movie, User selectedUser) {
        ratingManager.rate(selectedRating, movie, selectedUser);
    }
}
