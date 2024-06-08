package Services;

public class PayrollService {

    public static double getSemiMonthlyRate(double BasicSalary) {
        return BasicSalary / 2;
    }

    public static double getHourlyRate(double BasicSalary) {
        double hourlyRate = (BasicSalary / 21) / 8;
        return Math.round(hourlyRate * 100.0) / 100.0;
    }

}
