package DAL.file;

import BE.Movie;
import BE.User;
import DAL.IUserDataAccess;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDataAccess {
    //get the list of the users
    private static final String USER_SOURCE =
            "D:\\onedrive2\\OneDrive - Erhvervsakademi Sydvest\\" +
                    "week 45\\netfilx\\Netflix_project\\data\\users.txt";

    public List<User> getAllUsers()
    {
        List<User> allUsers = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(new File(USER_SOURCE))))
        {
            boolean hasLines = true;
            while(hasLines){
                String line = br.readLine();
                if(line==null)
                    hasLines=false;
                if(hasLines)
                {
                    try{  allUsers.add(makeObjectFromString(line));} catch (NumberFormatException e) {
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
        return allUsers;
    }

    private User makeObjectFromString(String line) {
        String[] splittedLine = line.split(",");
        int ID = Integer.parseInt(splittedLine[0]);
        String name = splittedLine[1];

        User user = new User(ID, name);
        return user;

    }


}
