package View;

import Controller.EmployeeInterfaceController;
import Model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeInterface {

    public static EmployeeInterfaceController initializeComponents(ActionEvent event, Employee employee) throws IOException {
        FXMLLoader loader = new FXMLLoader(EmployeeInterface.class.getResource("/View/EmployeeInterface.fxml"));
        Parent root = loader.load();

        EmployeeInterfaceController controller = loader.getController();
        controller.setEmployeeDetails(employee);
        controller.setPayrollDetails(employee);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        return controller;
    }

}
