package Services;

public class PayrollService {

    public static double getSemiMonthlyRate(double BasicSalary) {
        return BasicSalary / 2;
    }

    public static double getHourlyRate(double BasicSalary) {
        return (BasicSalary / 21) / 8;
    }

}
