package Controller;

import Model.DataHandler;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;


public class AdminController {

    public static void addEmployee() {

    }

    public static void udpateEmployeeData(int employee_id) {

    }

    public static void deleteEmployee(int employee_id) throws IOException, CsvException {
        DataHandler.deleteEmployeeByID(employee_id);
    }
}

