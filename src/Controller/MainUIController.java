package Controller;


import Model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainUIController {

    @FXML
    private StackPane contentArea;

    @FXML
    private Label welcome_Label;

    @FXML
    private Button manage_Button;

    @FXML
    private Button payroll_Button;

    private Employee employee;

    private void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public static MainUIController initializeUI(ActionEvent event, Employee employee) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainUIController.class.getResource("/View/MainUI.fxml"));
        Parent root = loader.load();

        MainUIController controller = loader.getController();
        controller.setWelcomeLabel(employee);
        controller.setEmployee(employee);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        return controller;
    }

    @FXML
    private void setWelcomeLabel(Employee employee) {
        welcome_Label.setText("Welcome " + employee.getFirstName() + " " + employee.getLastName() + " !");
    }

    @FXML
    public void switchToMyProfileInterface(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/MyProfileInterface.fxml"));
        Parent root = loader.load();

        MyProfileController profileController = loader.getController();
        profileController.setEmployeeDetails(employee);

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @FXML
    public void switchToPayslipInterface(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/PayslipInterface.fxml"));
        Parent root = loader.load();

        PayslipInterfaceController payDetailsController = loader.getController();
        payDetailsController.initialize(employee);

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @FXML
    private void switchToManageInterface(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/EmployeeManagementInterface.fxml"));
        Parent root = loader.load();

        EmployeeManagementController employeeManagementController = loader.getController();
        employeeManagementController.initialize();

        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    public void disableAdminButtons() {
        manage_Button.setVisible(false);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("/View/LoginForm.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene loginScene = new Scene(loginRoot);
        stage.setScene(loginScene);
        stage.show();
    }

}

