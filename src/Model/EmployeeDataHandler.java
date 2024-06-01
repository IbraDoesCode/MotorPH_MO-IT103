package Model;

import com.opencsv.exceptions.CsvException;
import javafx.scene.control.Alert;
import ui.AlertUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDataHandler {

    private static final String EMPLOYEES_DATA_FILE = "Data/Employee_data.csv";

    public static List<Employee> retrieveEmployees() throws IOException, CsvException {
        List<String[]> rows = CSVUtils.retrieveCSVData(EMPLOYEES_DATA_FILE);
        List<Employee> employees = new ArrayList<>();

        for (String[] row : rows) {
            Employee employee = new Employee(
                    Integer.parseInt(row[0]),
                    row[1],
                    row[2],
                    row[3],
                    row[4],
                    row[5],
                    row[6],
                    row[7],
                    row[8],
                    row[9],
                    row[10],
                    row[11],
                    row[12],
                    row[13],
                    Double.parseDouble(row[14]),
                    Double.parseDouble(row[15]),
                    Double.parseDouble(row[16]),
                    Double.parseDouble(row[17]),
                    Double.parseDouble(row[18]),
                    Double.parseDouble(row[19])
            );
            employees.add(employee);
        }
        return employees;
    }

    public static Employee retrieveEmployeeByID(int employee_id) throws IOException, CsvException {
        List<Employee> employees = retrieveEmployees();
        for (Employee employee : employees) {
            if (employee.getEmployeeID() == employee_id) {
                return employee;
            }
        }
        return null;
    }

    public static void deleteEmployeeRecord(int employee_id) throws IOException, CsvException {
        List<String[]> csvdata = CSVUtils.retrieveCSVData(EMPLOYEES_DATA_FILE);
        boolean found = false;

        // Iterate over the List
        for (int i = 0; i < csvdata.size(); i++) {
            // Retrieve ID# from current row (index i) at the first column (index 0).
            int id = Integer.parseInt(csvdata.get(i)[0]);
            if (id == employee_id) {
                csvdata.remove(i);
                found = true;
                break;
            }
        }

        if (!found){
            System.out.println("EmployeeID: " + employee_id + " not found");
            return;
        }

        CSVUtils.writeDataToCSV(EMPLOYEES_DATA_FILE, csvdata);
        System.out.println("Record deleted.");
    }

}
