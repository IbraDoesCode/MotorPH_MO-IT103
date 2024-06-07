package Controller;

import Model.Employee;
import Model.EmployeeDataHandler;
import com.opencsv.exceptions.CsvException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ui.AlertUtil;

import java.io.IOException;
import java.util.List;

public class EmployeeManagementController {

    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Integer> employeeID_Column;
    @FXML
    private TableColumn<Employee, String> lastName_Column;
    @FXML
    private TableColumn<Employee, String> firstName_Column;
    @FXML
    private TableColumn<Employee, String> sssNo_Column;
    @FXML
    private TableColumn<Employee, String> philhealthNo_Column;
    @FXML
    private TableColumn<Employee, String> pagibigNo_Column;
    @FXML
    private TableColumn<Employee, String> tinNo_Column;
    @FXML
    private StackPane contentArea;


    public void initialize() {
        initializeTableView();
        loadEmployeeData();
    }

    public void initializeTableView() {
        employeeID_Column.setCellValueFactory(new PropertyValueFactory<>("employeeID"));
        lastName_Column.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstName_Column.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        sssNo_Column.setCellValueFactory(new PropertyValueFactory<>("sssNumber"));
        philhealthNo_Column.setCellValueFactory(new PropertyValueFactory<>("philhealthNumber"));
        tinNo_Column.setCellValueFactory(new PropertyValueFactory<>("pagibigNumber"));
        pagibigNo_Column.setCellValueFactory(new PropertyValueFactory<>("tinNumber"));
    }

    public void loadEmployeeData() {
        try {
            List<Employee> employees = EmployeeDataHandler.retrieveEmployees();
            ObservableList<Employee> employeeObservableList = FXCollections.observableArrayList(employees);
            employeeTable.setItems(employeeObservableList);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToViewEmployeeInterface(ActionEvent event) throws IOException {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee == null) {
            AlertUtil.showAlert(Alert.AlertType.INFORMATION, "No Employee Selected", "Please select an employee");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/ViewEmployeeInterface.fxml"));
        Parent root = loader.load();

        ViewEmployeeController controller = loader.getController();
        controller.setEmployeeDetails(selectedEmployee);

        contentArea = (StackPane) ((Node) event.getSource()).getScene().lookup("#contentArea");
        contentArea.getChildren().clear();
        contentArea.getChildren().add(root);
    }

}
