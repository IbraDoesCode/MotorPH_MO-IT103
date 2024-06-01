package Model;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDataHandler {

    private static final String USERS_CREDENTIALS_FILE = "Data/User_credentials.csv";

    public static List<User> retrieveUserCredentials() throws IOException, CsvException{
        List<String[]> rows = CSVUtils.retrieveCSVData(USERS_CREDENTIALS_FILE);
        List<User> users = new ArrayList<>();

        for (String[] row : rows) {
            users.add(new User(
                    Integer.parseInt(row[0]),
                    row[1],
                    row[2],
                    row[3],
                    row[4],
                    row[5],
                    row[6],
                    row[7]
            ));
        }
        return users;
    }

    public static User retrieveUserCredentialByUsername(String username) throws IOException, CsvException{
        List<User>users = retrieveUserCredentials();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
