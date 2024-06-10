package Services;

import Model.DataHandler;
import Model.Employee;
import Model.EmployeeDataHandler;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class PayrollService {

    public static double getSemiMonthlyRate(double BasicSalary) {
        return BasicSalary / 2;
    }

    public static double getHourlyRate(double BasicSalary) {
        double hourlyRate = (BasicSalary / 21) / 8;
        return Math.round(hourlyRate * 100.0) / 100.0;
    }

    public static double getBasicSalary(Employee employee) throws IOException, CsvException {
        List<Employee> employeeData = DataHandler.retrieveEmployeeList();
        for (Employee emp : employeeData) {
            if (emp.getEmployeeID() == employee.getEmployeeID()) {
                return emp.getBasicSalary();
            }
        }
        return 0;
    }


    private static double getHoursWorked(Employee employee, int month) throws IOException, CsvException {
        int employee_id = employee.getEmployeeID();
        return AttendanceService.retrieveHoursWorked(employee_id, month);
    }


    public static double getTotalAllowances(Employee employee) throws IOException, CsvException {
        List<Employee> employeeData = DataHandler.retrieveEmployeeList();
        for (Employee emp : employeeData) {
            if (emp.getEmployeeID() == employee.getEmployeeID()) {
                return emp.getRiceSubsidy() + emp.getClothingAllowance() + emp.getPhoneAllowance();
            }
        }
        return 0;
    }


    public static double calculateGross(Employee employee, int month) throws IOException, CsvException {
        double hoursWorked = getHoursWorked(employee, month);
        return Math.round((employee.getHourlyRate() * hoursWorked + getTotalAllowances(employee)) * 100.0) / 100.0;
    }

    public static double calculateSSS(double basicSalary) {
        try (CSVReader reader = new CSVReader(new FileReader("Data/sss_contribution_table.csv"))) {
            String[] line;
            reader.skip(1);
            while ((line = reader.readNext()) != null) {
                double min = Double.parseDouble(line[0]);
                double max = Double.parseDouble(line[1]);
                double rate = Double.parseDouble(line[2]);
                if (basicSalary >= min && basicSalary <= max) {
                    return rate;
                }
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static double calculatePhilhealth(double basicSalary) {
        return (basicSalary * 0.03) / 2;
    }

    public static double calculatePagibig(double basicSalary) {
        if (basicSalary > 1000 && basicSalary < 1500) {
            return basicSalary * 0.01;
        } else {
            return basicSalary * 0.02;
        }
    }

    private static double calculatePartialDeduction(double basicSalary) {
        double sss = calculateSSS(basicSalary);
        double philhealth = calculatePhilhealth(basicSalary);
        double pagibig = calculatePagibig(basicSalary);

        return sss + philhealth + pagibig;
    }

    public static double calculateWithholdingTax(double gross) {
        double withholdingTax = 0;

        if (gross <= 20832) {
            withholdingTax = 0; // No withholding tax
        } else if (gross > 20832 && gross < 33333) {
            withholdingTax = (gross - 20832) * 0.20;
        } else if (gross >= 33333 && gross < 66667) {
            withholdingTax = 2500 + (gross - 33333) * 0.25;
        } else if (gross >= 66667 && gross < 166667) {
            withholdingTax = 10833 + (gross - 66667) * 0.30;
        } else if (gross >= 166667 && gross < 666667) {
            withholdingTax = 40833.33 + (gross - 166667) * 0.32;
        } else if (gross >= 666667) {
            withholdingTax = 200833.33 + (gross - 666667) * 0.35;
        }
        return withholdingTax;
    }

    public static double calculateNetPay(Employee employee, int month) throws IOException, CsvException {
        double gross = calculateGross(employee, month);
        double basic = getBasicSalary(employee);
        double deductions = calculatePartialDeduction(basic);
        double tax = calculateWithholdingTax(gross);
        return Math.round((gross - deductions - tax) * 100.0) / 100.0;
    }
}

