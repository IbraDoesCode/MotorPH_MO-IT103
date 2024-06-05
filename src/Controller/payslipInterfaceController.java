package Controller;

import Model.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class payslipInterfaceController {

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

    public void setPayrollDetails(Employee employee) {
        basicSalary_TextField.setText(String.valueOf(employee.getBasicSalary()));
        riceSubsidy_TextField.setText(String.valueOf(employee.getRiceSubsidy()));
        phoneAllowance_TextField.setText(String.valueOf(employee.getPhoneAllowance()));
        clothingAllowance_TextField.setText(String.valueOf(employee.getClothingAllowance()));
        semiMonthly_TextField.setText(String.valueOf(employee.getGrossSemiMonthlyRate()));
        hourlyRate_TextField.setText(String.valueOf(employee.getHourlyRate()));
    }
}
