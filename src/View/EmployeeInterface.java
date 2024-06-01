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

    public static void initialize(ActionEvent event, Employee employee){

        try {
            FXMLLoader loader = new FXMLLoader(EmployeeInterface.class.getResource("/View/EmployeeInterface.fxml"));
            Parent root = loader.load();

            EmployeeInterfaceController employeeInterface = loader.getController();
            employeeInterface.setEmployeeDetails(employee);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
