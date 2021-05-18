package services;

import entities.Employee;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class EmployeeServiceTest {
    private EmployeeService employeeService;

    @Before
    public void createInstances(){
        this.employeeService = new EmployeeService();
    }

    @Test
    public void testCalculatingScoreShouldReturnScoreWithoutExperienceMultiplier(){
        Employee employee = new Employee("In", 10, 5, 3);

        double score = 1.0*employee.getTotalSales() / employee.getSalesPeriod();

        Assert.assertEquals(score, employeeService.calculateScoreForEmployee(employee, false), 0.0001);
    }
    @Test
    public void testCalculatingScoreShouldReturnScoreWithExperienceMultiplier(){
        Employee employee = new Employee("In", 10, 5, 3);

        double score = 1.0*employee.getTotalSales() / employee.getSalesPeriod() * employee.getExperienceMultiplier();

        Assert.assertEquals(score, employeeService.calculateScoreForEmployee(employee,true), 0.0001);
    }


    @Test(expected = IOException.class)
    public void testReadDataShouldThrownExceptionIfFileNameOrPathIsIncorrect() throws IOException, ParseException {
        this.employeeService.readDataForEmployees("Wrong Path!");
    }

    @Test(expected = ClassCastException.class)
    public void testReadDataShouldThrownExceptionIfDataCantBeCast() throws IOException, ParseException {


        this.employeeService.readDataForEmployees("src/main/resources/report-definition.json");

    }
    @Test(expected = ParseException.class)
    public void testReadDataShouldThrownExceptionIfDataCantBeParsed() throws IOException, ParseException {

        this.employeeService.readDataForEmployees("src/main/resources/data-test.json");

    }
}
