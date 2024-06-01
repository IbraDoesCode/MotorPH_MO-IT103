package Model;


import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;

public class DataHandler {


    public static Employee retrieveEmployeeByID(int employee_id) throws IOException, CsvException {
        return EmployeeDataHandler.retrieveEmployeeByID(employee_id);
    }

    public static void deleteEmployeeByID(int employee_id) throws IOException, CsvException{
        EmployeeDataHandler.deleteEmployeeRecord(employee_id);
    }

    public static void updateEmployeeRecord(int employee_id, String[]newData) throws IOException, CsvException{
        EmployeeDataHandler.updateEmployeeRecord(employee_id, newData);
    }

    public static List<User> retrieveUserCredentials() throws IOException, CsvException{
        return UserDataHandler.retrieveUserCredentials();
    }

    public static User retrieveUserCredentialByUsername(String username) throws IOException, CsvException{
        return UserDataHandler.retrieveUserCredentialByUsername(username);
    }
}
