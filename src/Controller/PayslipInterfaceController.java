package Controller;

import Model.Employee;
import Services.AttendanceService;
import Services.PayrollService;
import com.opencsv.exceptions.CsvException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ui.AlertUtil;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PayslipInterfaceController {

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
    private TextField gross_TextField;
    @FXML
    private TextField sssAmount_TextField;
    @FXML
    private TextField phic_TextField;
    @FXML
    private TextField hdmf_TextField;
    @FXML
    private TextField tax_TextField;
    @FXML
    private TextField allowance_TextField;
    @FXML
    private TextField net_TextField;

    @FXML
    private ComboBox<String> comboBox;

    private Employee employee;

    public void initialize(Employee employee) {
        this.employee = employee;
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
        displayHoursWorked();
        updateDeductions();
        displayGross();
        displayAllowance();
    }

    private void initializeComboBox() {
        String[] months = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        comboBox.getItems().addAll(months);
        comboBox.getItems().removeFirst();
        comboBox.getSelectionModel().select(0);

        comboBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                displayHoursWorked();
                displayGross();
                updateDeductions();
            }
        });
    }

    private void displayHoursWorked() {
        try {
            int employeeId = employee.getEmployeeID();
            double hoursWorked = retrieveHoursWorked(employeeId);
            hoursWorked_TextField.setText(String.valueOf(hoursWorked));
        } catch (FileNotFoundException e) {
            String selectedMonth = comboBox.getSelectionModel().getSelectedItem();
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Error", "Record for " + selectedMonth + " not found");
            comboBox.getSelectionModel().select(0);
        } catch (IOException | CsvException e) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while retrieving hours worked.");
        }
    }

    private int getMonthSelected() {
        int selectedIndex = comboBox.getSelectionModel().getSelectedIndex();
        return selectedIndex == -1 ? 1 : selectedIndex + 1;
    }

    private double retrieveHoursWorked(int employee_id) throws IOException, CsvException{
        int month = getMonthSelected();
        return AttendanceService.retrieveHoursWorked(employee_id, month);

    }

    private void displayGross() {
        try {
            int month = getMonthSelected();
            double gross = PayrollService.calculateGross(employee, month);
            gross_TextField.setText(String.valueOf(gross));
        } catch (IOException | CsvException e) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while retrieving data");
        }
    }

    private void displayAllowance() {
        try {
            double totalAllowance = PayrollService.getTotalAllowances(employee);
            allowance_TextField.setText(String.valueOf(totalAllowance));
        } catch (IOException | CsvException e) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while retrieving data");
        }
    }

    private void updateDeductions() {
        try {
            double sss = PayrollService.calculateSSS(employee.getBasicSalary());
            double philhealth = PayrollService.calculatePhilhealth(employee.getBasicSalary());
            double pagibig = PayrollService.calculatePagibig(employee.getBasicSalary());
            sssAmount_TextField.setText(String.valueOf(sss));
            phic_TextField.setText(String.valueOf(philhealth));
            hdmf_TextField.setText(String.valueOf(pagibig));

            double gross = PayrollService.calculateGross(employee, getMonthSelected());
            double tax = PayrollService.calculateWithholdingTax(gross);
            tax_TextField.setText(String.valueOf(tax));
            double net = PayrollService.calculateNetPay(employee, getMonthSelected());
            net_TextField.setText(String.valueOf(net));
        } catch (IOException | CsvException e) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Error", "An error occurred while updating deductions.");
        }
    }
}
