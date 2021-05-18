package services;

import entities.Employee;
import entities.Report;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ReportServiceTest {
    private ReportService reportService;

    @Before
    public void createInstances(){
        this.reportService = new ReportService();
    }

    @Test
    public void testCorrectGeneratedReport() throws IOException, ParseException {
        this.reportService.generate("src/main/resources/report-definition.json");
    }

    @Test(expected = IOException.class)
    public void testReadDataShouldThrownExceptionIfFileNameOrPathIsIncorrect() throws IOException, ParseException {
        this.reportService.generate("Wrong Path!");
    }

    @Test(expected = ParseException.class)
    public void testReadDataShouldThrownExceptionIfDataCantBeParsed() throws IOException, ParseException {


        this.reportService.generate("src/main/resources/report-definition-test.json");

    }

}
