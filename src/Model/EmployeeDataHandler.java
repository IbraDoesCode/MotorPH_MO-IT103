package Model;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDataHandler {

    private static final String EMPLOYEES_DATA_FILE = "Data/Employee_data.csv";
    private static final String[] HEADERS = {
            "Employee #",
            "Last Name",
            "First Name",
            "Birthday",
            "Address",
            "Phone Number",
            "SSS #",
            "Philhealth #",
            "TIN #",
            "Pag-ibig #",
            "Status",
            "Position",
            "Department",
            "Immediate Supervisor",
            "Basic Salary",
            "Rice Subsidy",
            "Phone Allowance",
            "Clothing Allowance",
            "Gross Semi-monthly Rate",
            "Hourly Rate"
    };

    public static List<Employee> retrieveEmployees() throws IOException, CsvException {
        List<String[]> data = CSVUtils.retrieveCSVData(EMPLOYEES_DATA_FILE);
        List<Employee> employees = new ArrayList<>();

        for (String[] row : data) {

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
                // if found remove current row from List.
                csvdata.remove(i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("EmployeeID: " + employee_id + " not found");
            return;
        }

        // Write new List to CSV
        CSVUtils.writeDataToCSV(EMPLOYEES_DATA_FILE, csvdata, HEADERS);
        System.out.println("Record deleted.");
    }

    public static void updateEmployeeRecord(int employee_id, String[] newData) throws IOException, CsvException {
        List<String[]> csvdata = CSVUtils.retrieveCSVData(EMPLOYEES_DATA_FILE);
        boolean found = false;

        for (int i = 0; i < csvdata.size(); i++) {
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

        CSVUtils.writeDataToCSV(EMPLOYEES_DATA_FILE, csvdata, HEADERS);
        System.out.println("Record updated.");
    }

    public static void addEmployeeRecord(String[] newData) throws IOException, CsvException {
        List<String[]> csvdata = CSVUtils.retrieveCSVData(EMPLOYEES_DATA_FILE);
        csvdata.add(newData);
        CSVUtils.writeDataToCSV(EMPLOYEES_DATA_FILE, csvdata, HEADERS);
    }
}
