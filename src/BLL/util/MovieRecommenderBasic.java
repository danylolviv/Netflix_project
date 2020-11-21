package BLL.util;

import DAL.MovieDAO;
import DAL.RatingDAO;
import DAL.UserDAO;

public class MovieRecommenderBasic {

        private MovieDAO movieDAO;
        private UserDAO userDAO;
        private RatingDAO ratingDAO;

        public MovieRecommenderBasic() {
            movieDAO = new MovieDAO();
            userDAO = new UserDAO();
        }

    /**
     * Calculate the average rating for all the books
     * in the library
     *
     *add them to the ArraylIst
     *
     * sort
     */

}
