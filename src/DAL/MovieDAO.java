package DAL;

import BE.Movie;

import java.io.*;
import java.util.ArrayList;
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



    public List<Movie> getAllMovies()  {
        List<Movie> allMovies = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(new File(MOVIE_SOURCE))))
        {
            boolean hasLines = true;
            while(hasLines){
                String line = br.readLine();
                if(line==null)
                    hasLines=false;
                if(hasLines)
                {
                  try{  allMovies.add(makeObjectFromString(line));} catch (NumberFormatException e) {
                      //e.printStackTrace();
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
            String movieString = movie.getId() + "," + movie.getTitle() + "," + movie.getYear();
            bw.append(movieString);
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Movie movie) {
    String newFileString = "";
        //the true keyword below is very important
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(MOVIE_SOURCE, true)))
        {
        Scanner scanner = new Scanner(String.valueOf(bw));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                int id = Integer.parseInt(fields[0].trim());
                if (id != movie.getId()) {
                    newFileString += line;
                }
                //we can change everything but not ID
                if(id == movie.getId())
                {
                    String newMovie = movie.getId() + ',' + movie.getTitle() +',' +
                            movie.getYear();
                    bw.append(newMovie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bw
                     = new BufferedWriter(
                new FileWriter(MOVIE_SOURCE))) {
            bw.write(newFileString);
        } catch (IOException e) {
            e.printStackTrace();
        }
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



