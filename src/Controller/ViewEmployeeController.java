package Controller;

import Model.DataHandler;
import Model.Employee;
import Model.EmployeeDataHandler;
import Services.PayrollService;
import com.opencsv.exceptions.CsvException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import org.w3c.dom.Text;
import ui.AlertUtil;

import java.io.IOException;

public class ViewEmployeeController {

    @FXML
    private TextField fullname_TextField;
    @FXML
    private TextField dob_TextField;
    @FXML
    private TextField contactNo_TextField;
    @FXML
    private TextArea address_TextArea;
    @FXML
    private TextField employeeId_TextField;
    @FXML
    private TextField supervisor_TextField;
    @FXML
    private TextField department_TextField;
    @FXML
    private TextField position_TextField;
    @FXML
    private TextField status_TextField;
    @FXML
    private TextField sss_TextField;
    @FXML
    private TextField philhealth_TextField;
    @FXML
    private TextField tin_TextField;
    @FXML
    private TextField pagIbig_TextField;
    @FXML
    private TextField basicSalary_TextField;
    @FXML
    private TextField riceSubsidy_TextField;
    @FXML
    private TextField clothingAllowance_TextField;
    @FXML
    private TextField phoneAllowance_TextField;
    @FXML
    private StackPane contentArea;

    public void setEmployeeDetails(Employee employee) {
        fullname_TextField.setText(employee.getFullName());
        dob_TextField.setText(employee.getBirthday());
        contactNo_TextField.setText(employee.getPhoneNumber());
        address_TextArea.setText(employee.getAddress());
        employeeId_TextField.setText(String.valueOf(employee.getEmployeeID()));
        supervisor_TextField.setText(employee.getImmediateSupervisor());
        department_TextField.setText(employee.getDepartment());
        position_TextField.setText(employee.getPosition());
        status_TextField.setText(employee.getStatus());
        sss_TextField.setText(employee.getSssNumber());
        philhealth_TextField.setText(employee.getPhilhealthNumber());
        tin_TextField.setText(employee.getTinNumber());
        pagIbig_TextField.setText(employee.getPagibigNumber());
        basicSalary_TextField.setText(String.valueOf(employee.getBasicSalary()));
        riceSubsidy_TextField.setText(String.valueOf(employee.getRiceSubsidy()));
        clothingAllowance_TextField.setText(String.valueOf(employee.getClothingAllowance()));
        phoneAllowance_TextField.setText(String.valueOf(employee.getPhoneAllowance()));
    }

    @FXML
    private void switchToEmployeeManagementInterface(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EmployeeManagementInterface.fxml"));
        Parent root = loader.load();
        contentArea = (StackPane) ((Node) event.getSource()).getScene().lookup("#contentArea");
        contentArea.getChildren().clear();
        contentArea.getChildren().add(root);

        EmployeeManagementController employeeManagementController = loader.getController();
        employeeManagementController.initialize();
    }

    @FXML
    private void deleteEmployeeRecord() {
        int employee_id = Integer.parseInt(employeeId_TextField.getText());
        boolean confirmed = AlertUtil.showConfirmationAlert("Warning", "Delete Employee Record?");

        if (confirmed) {
            try {
                EmployeeDataHandler.deleteEmployeeRecord(employee_id);
                AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Success", "Employee Record Deleted.");
            } catch (IOException | CsvException e) {
                AlertUtil.showAlert(Alert.AlertType.ERROR, "Error!", e.getMessage());
            }
        }

    }

    @FXML
    private void updateEmployeeRecord() {
        String[] fullName = fullname_TextField.getText().split(" ");

        String employee_id = employeeId_TextField.getText();
        String lastname = fullName.length > 1 ? fullName[1] : "";
        String firstname = fullName.length > 0 ? fullName[0] : "";
        String dob = dob_TextField.getText();
        String address = address_TextArea.getText();
        String phoneNo = contactNo_TextField.getText();
        String sss = sss_TextField.getText();
        String philhealth = philhealth_TextField.getText();
        String tin = tin_TextField.getText();
        String pagibig = pagIbig_TextField.getText();
        String status = status_TextField.getText();
        String position = position_TextField.getText();
        String department = department_TextField.getText();
        String supervisor = supervisor_TextField.getText();
        String basicSalary = basicSalary_TextField.getText();
        String riceSubsidy = riceSubsidy_TextField.getText();
        String clothingAllowance = clothingAllowance_TextField.getText();
        String phoneAllowance = phoneAllowance_TextField.getText();
        String semiMonthly = "";
        String hourlyRate = "";


        if (employee_id.isEmpty() || lastname.isEmpty() || firstname.isEmpty() || dob.isEmpty() || address.isEmpty() ||
                phoneNo.isEmpty() || sss.isEmpty() || philhealth.isEmpty() || tin.isEmpty() || pagibig.isEmpty() ||
                status.isEmpty() || position.isEmpty() || department.isEmpty() || supervisor.isEmpty() || basicSalary.isEmpty() ||
                riceSubsidy.isEmpty() || clothingAllowance.isEmpty() || phoneAllowance.isEmpty()) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Error", "All fields are required");
            return;
        }


        try {
            semiMonthly = String.valueOf(PayrollService.getSemiMonthlyRate(Double.parseDouble(basicSalary)));
            hourlyRate = String.valueOf(PayrollService.getHourlyRate(Double.parseDouble(basicSalary)));
        } catch (NumberFormatException e) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Error", "Invalid numeric input: " + e.getMessage());
            return;
        }

        String[] employeeData = {employee_id, lastname, firstname, dob, address, phoneNo, sss, philhealth, tin, pagibig,
                status, position, department, supervisor, basicSalary, riceSubsidy, clothingAllowance, phoneAllowance,
                semiMonthly, hourlyRate};

        boolean confirmed = AlertUtil.showConfirmationAlert("Warning", "Update Employee Record?");

        if (confirmed) {
            int id = Integer.parseInt(employee_id);
            try {
                DataHandler.updateEmployeeRecord(id, employeeData);
                AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Success", "Employee record updated");
            } catch (IOException | CsvException e) {
                AlertUtil.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
            }
        }
    }

}
