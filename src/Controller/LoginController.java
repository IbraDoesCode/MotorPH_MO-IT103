package Controller;

import Model.DataHandler;
import Model.Employee;
import Model.User;
import View.MainInterface;
import com.opencsv.exceptions.CsvException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.AlertUtil;

import java.io.IOException;


public class LoginController {

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    public void login(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();
        try {

            if (username.isEmpty() || password.isEmpty()) {
                AlertUtil.showAlert(Alert.AlertType.ERROR, "Login Error", "Please enter both username and password.");
                return;
            }

            if (!AuthenticationHandler.authenticate(username, password)) {
                AlertUtil.showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid Credentials");
                return;
            }

            User user = DataHandler.retrieveUserCredentialByUsername(username);
            Employee employee = DataHandler.retrieveEmployeeByID(user.getEmployee_id());

            AlertUtil.showAlert(Alert.AlertType.INFORMATION, "Login Success", "Welcome " + user.getFirst_name());

            MainInterfaceController controller = MainInterface.initializeComponents(event, employee);

            if (!user.isAdmin()) {
                controller.hideAdminComponents();
            }

        } catch (IOException | CsvException e) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        } catch (Exception e) {
            AlertUtil.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
            System.out.println(e.getMessage());
        }
    }
}