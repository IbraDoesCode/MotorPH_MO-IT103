package Controller;

import Model.Employee;
import Services.AttendanceService;
import com.opencsv.exceptions.CsvException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class payslipInterfaceController {

    @FXML
    private TextField basicSalary_TextField;
    @FXML
    private TextField riceSubsidy_TextField;
    @FXML
    private TextField phoneAllowance_TextField;
    @FXML
    private TextField clothingAllowance_TextField;
    @FXML
    private TextField semiMonthly_TextField;
    @FXML
    private TextField hourlyRate_TextField;
    @FXML
    private TextField hoursWorked_TextField;
    @FXML
    private ComboBox<String> comboBox;

    public void initialize(Employee employee) {
        setPayrollDetails(employee);
        initializeComboBox();
    }

    private void setPayrollDetails(Employee employee) {
        basicSalary_TextField.setText(String.valueOf(employee.getBasicSalary()));
        riceSubsidy_TextField.setText(String.valueOf(employee.getRiceSubsidy()));
        phoneAllowance_TextField.setText(String.valueOf(employee.getPhoneAllowance()));
        clothingAllowance_TextField.setText(String.valueOf(employee.getClothingAllowance()));
        semiMonthly_TextField.setText(String.valueOf(employee.getGrossSemiMonthlyRate()));
        hourlyRate_TextField.setText(String.valueOf(employee.getHourlyRate()));
        hoursWorked_TextField.setText(String.valueOf(retrieveHoursWorked(employee.getEmployeeID())));
    }

    private void initializeComboBox() {
        String[] months = {"","January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        comboBox.getItems().addAll(months);
        comboBox.getItems().removeFirst();
        comboBox.getSelectionModel().select(0);
    }

    private int getMonthSelected() {
        int selectedIndex = comboBox.getSelectionModel().getSelectedIndex();
        return selectedIndex == -1 ? 1 : selectedIndex + 1;
    }

    private double retrieveHoursWorked(int employee_id) {
        double totalHours = 0;
        int month = getMonthSelected();
        try {
            totalHours = AttendanceService.retrieveHoursWorked(employee_id, month);
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
        return totalHours;
    }
}
