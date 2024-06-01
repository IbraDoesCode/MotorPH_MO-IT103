package Model;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDataHandler {

    private static final String EMPLOYEES_DATA_FILE = "Data/Employee_data.csv";

    public static List<Employee> retrieveEmployees() throws IOException, CsvException {
        List<String[]> rows = CSVUtils.retrieveCSVData(EMPLOYEES_DATA_FILE);
        List<Employee> employees = new ArrayList<>();

        boolean firstRow = true;

        for (String[] row : rows) {
            // skip column 0 headers.
            if (firstRow) {
                firstRow = false;
                continue;
            }
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

        if (!found) {
            System.out.println("EmployeeID: " + employee_id + " not found");
            return;
        }

        CSVUtils.writeDataToCSV(EMPLOYEES_DATA_FILE, csvdata);
        System.out.println("Record deleted.");
    }

    public static void updateEmployeeRecord(int employee_id, String[] newData) throws IOException, CsvException {
        List<String[]> csvdata = CSVUtils.retrieveCSVData(EMPLOYEES_DATA_FILE);
        boolean found = false;

        // Start loop at index 1 to skip headers.
        for (int i = 1; i < csvdata.size(); i++) {
            int id = Integer.parseInt(csvdata.get(i)[0]);
            if (id == employee_id) {
                if (newData.length == csvdata.get(i).length) {
                    csvdata.set(i, newData);
                    found = true;
                    break;
                } else {
                    System.out.println("Expected data: " + csvdata.get(i).length + " received: " + newData.length);
                }
            }
        }

        if (!found) {
            System.out.println("EmployeeID: " + employee_id + " not found");
            return;
        }

        CSVUtils.writeDataToCSV(EMPLOYEES_DATA_FILE, csvdata);
        System.out.println("Record updated.");
    }

}
