package Controller;

import Model.DataHandler;
import Model.User;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;

public class AuthenticationHandler {

    public static boolean authenticate(String username, String password) throws IOException, CsvException{
        List<User> users = DataHandler.retrieveUserCredentials();
        for (User user : users) {
            if (user.validateCredentials(username, password)) {
                return true;
            }
        }
        return false;
    }
}
