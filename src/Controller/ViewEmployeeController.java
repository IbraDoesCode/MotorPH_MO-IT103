package Controller;

import Model.Employee;
import Model.EmployeeDataHandler;
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
}
