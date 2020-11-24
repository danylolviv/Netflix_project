package DAL.file;

import BE.Movie;
import DAL.IMovieDataAccess;
import DAL.exception.MrsDalException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class MovieDAO implements IMovieDataAccess {
    //CRUD operations
    // getAllMoviesMethod
    //Update a certain Movie
    // delate a certain movie

    private static final String MOVIE_SOURCE =
            "D:\\onedrive2\\OneDrive - Erhvervsakademi Sydvest\\" +
                    "week 45\\netfilx\\Netflix_project\\data\\movie_titles.txt";


    @Override
    public List<Movie> getAllMovies()  {
        List<Movie> allMovies = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(new File(MOVIE_SOURCE))))
        {
            boolean hasLines = true;
            while(hasLines){
                String line = br.readLine();
                if(line==null)
                    hasLines=false;
                if(hasLines && !line.isBlank())
                {


                  try{  allMovies.add(makeObjectFromString(line));} catch (NumberFormatException e) {
                      e.printStackTrace();
                      System.out.println("Number format exception: "+ line);
                  }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allMovies;
    }
    @Override
    public List<Movie> searchForTheMovies(String text)
    {
        List<Movie> foundMovies = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(new File(MOVIE_SOURCE))))
        {
            boolean hasLines = true;
            while(hasLines){
                String line = br.readLine();
                if(line==null)
                    hasLines=false;
                if(hasLines)
                {
                    //check if current line contains the text
                    //if yes add that movie to the ArrayList
                    if(line.contains(text))
                    {
                        try{  foundMovies.add(makeObjectFromString(line));} catch (NumberFormatException e) {
                            //e.printStackTrace();
                            System.out.println("Number format exception: "+ line);
                        }

                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foundMovies;
    }

    @Override
    public void add(Movie movie) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(MOVIE_SOURCE, true)))
        {
            String movieString = movie.getId() +  "," + movie.getYear() +"," + movie.getTitle() ;
            bw.append(movieString);
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Movie movie) throws MrsDalException {
        createMovie(movie.getYear(), movie.getTitle(), movie);
        try {
            File tmp = new File(movie.hashCode() + ".txt"); //Creates a temp file for writing to.
            List<Movie> allMovies = getAllMovies();
            allMovies.removeIf((Movie t) -> t.getId() == movie.getId());
            allMovies.add(movie);

            //I'll sort the movies by their ID's
            allMovies.sort(Comparator.comparingInt(Movie::getId));

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(tmp))) {
                for (Movie mov : allMovies) {
                    bw.write(mov.getId() + "," + mov.getYear() + "," + mov.getTitle());
                    bw.newLine();
                }
            }

            //Overwrite the movie file wit the tmp one.
            Files.copy(tmp.toPath(), new File(MOVIE_SOURCE).toPath(), StandardCopyOption.REPLACE_EXISTING);
            //Clean up after the operation is done (Remve tmp)
            Files.delete(tmp.toPath());

        } catch (IOException ex) {
            throw new MrsDalException("Could not update movie.", ex);
        }

    }

    @Override
    public void delete(Movie movie) throws MrsDalException {
        try {
            File file = new File(MOVIE_SOURCE);
            if (!file.canWrite()) {
                throw new MrsDalException("Can't write to movie storage");
            }
            List<Movie> movies = getAllMovies();
            movies.remove(movie);
            OutputStream os = Files.newOutputStream(file.toPath());
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os))) {
                for (Movie mov : movies) {
                    String line = mov.getId() + "," + mov.getYear() + "," + mov.getTitle();
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException | MrsDalException ex) {
            throw new MrsDalException("Could not delete movie.", ex);
        }

    }
/*
    @Override
    public Movie getMovieByID(int movieID)  {
        List<Movie> allMovies = getAllMovies();
        try {
            for (Movie movie : allMovies
            ) {
                if (movieID == movie.getId())
                    return movie;

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("some issue here");
        }
        return null;

    }

 */


    public Movie createMovie(int releaseYear, String title, Movie movie)  {
        Path path = new File(MOVIE_SOURCE).toPath();
        int id = -1;
        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.SYNC, StandardOpenOption.APPEND, StandardOpenOption.WRITE)) {
           // id = getNextAvailableMovieID();
            id= movie.getId();
            bw.newLine();
            bw.write(id + "," + releaseYear + "," + title);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new Movie(id, title, releaseYear);
    }

    /**
     * Examines all stored movies and returns the next available highest ID.
     *
     * @return
     * @throws IOException
     */
    private int getNextAvailableMovieID()  {
        List<Movie> allMovies = getAllMovies();
        if (allMovies == null || allMovies.isEmpty()) {
            return 1;
        }
        allMovies.sort((Movie arg0, Movie arg1) -> arg0.getId() - arg1.getId());
        int id = allMovies.get(0).getId();
        for (int i = 0; i < allMovies.size(); i++) {
            if (allMovies.get(i).getId() <= id) {
                id++;
            } else {
                return id;
            }
        }
        return id;
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



