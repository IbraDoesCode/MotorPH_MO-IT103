package Services;

import Model.CSVUtils;
import Model.Employee;
import Model.EmployeeDataHandler;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class AttendanceService {

    private static String retrieveAttendanceFile(int month) throws FileNotFoundException {
        String[] months = {"", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        String filepath = "Data/Attendance/" + months[month] + "_attendance.csv";
        if (!CSVUtils.fileExists(filepath)) {
            throw new FileNotFoundException("No record found for month: " + month);
        }
        return filepath;
    }

    private static List<String[]> retrieveAttendanceData(int month) throws IOException, CsvException {
        String filepath = retrieveAttendanceFile(month);
        List<String[]> data = CSVUtils.retrieveCSVData(filepath);
        return data.subList(1, data.size());
    }

    public static double retrieveHoursWorked(int employee_id, int month) throws IOException, CsvException {
        List<String[]> attendanceData = retrieveAttendanceData(month);
        List<Employee> employeeList = EmployeeDataHandler.retrieveEmployees();
        double totalHours = 0;
        if (employee_id > 0 && employee_id < employeeList.size()) {
            for (String[] row : attendanceData) {
                int id = Integer.parseInt(row[0]);
                if (employee_id == id) {
                    double hoursPerDay = calculateHours(row);
                    totalHours += hoursPerDay;
                }
            }
        } else {
            throw new IllegalArgumentException("Employee ID not found");
        }
        totalHours = Math.round(totalHours * 100.0) / 100.0;
        return totalHours;
    }

    private static double calculateHours(String[] row) {
        String[] timeInData = row[2].split(":");
        String[] timeOutData = row[3].split(":");

        int timeInHour = Integer.parseInt(timeInData[0]);
        int timeInMinute = Integer.parseInt(timeInData[1]);
        int timeOutHour = Integer.parseInt(timeOutData[0]);
        int timeOutMinute = Integer.parseInt(timeOutData[1]);

        return (timeOutHour - timeInHour) + (timeOutMinute - timeInMinute) / 60.0;
    }

}
