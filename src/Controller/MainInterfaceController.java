package Controller;

import Model.Employee;
import Model.EmployeeDataHandler;
import com.opencsv.exceptions.CsvException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;


public class MainInterfaceController {

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab employeeManagement_Tab;

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

    // Payslip Tab
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
    private TextField net_TextField;


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
    }

    public void setPayrollDetails(Employee employee) {
        basicSalary_TextField.setText(String.valueOf(employee.getBasicSalary()));
        riceSubsidy_TextField.setText(String.valueOf(employee.getRiceSubsidy()));
        phoneAllowance_TextField.setText(String.valueOf(employee.getPhoneAllowance()));
        clothingAllowance_TextField.setText(String.valueOf(employee.getClothingAllowance()));
        semiMonthly_TextField.setText(String.valueOf(employee.getGrossSemiMonthlyRate()));
        hourlyRate_TextField.setText(String.valueOf(employee.getHourlyRate()));
    }

    public void hideAdminComponents() {
        tabPane.getTabs().remove(employeeManagement_Tab);
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

}
