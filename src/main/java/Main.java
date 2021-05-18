import entities.Employee;
import entities.Report;
import org.json.simple.parser.*;
import services.EmployeeService;
import services.ReportService;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        EmployeeService employeeService = new EmployeeService();
        ReportService reportService = new ReportService();

        String dataPath = args[0];
        String reportDefinitionPath = args[1];

        List<Employee> employees = employeeService.readDataForEmployees(dataPath);
        Report report = reportService.generate(reportDefinitionPath);


        employeeService.calculateScore(employees, report.isUseExperienceMultiplier());
        double minScoreBound = reportService.minScoreBound(employees, report);

        reportService.generateCSV(employees, report, minScoreBound);

    }
}
