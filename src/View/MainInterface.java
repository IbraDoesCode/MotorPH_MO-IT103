package View;

import Controller.MainInterfaceController;
import Model.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainInterface {

    public static MainInterfaceController initializeComponents(ActionEvent event, Employee employee) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainInterface.class.getResource("/View/MainInterface.fxml"));
        Parent root = loader.load();

        MainInterfaceController controller = loader.getController();
        controller.setEmployeeDetails(employee);
        controller.setPayrollDetails(employee);
        controller.initializeTableView();
        controller.loadEmployeeData();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        return controller;
    }

}
