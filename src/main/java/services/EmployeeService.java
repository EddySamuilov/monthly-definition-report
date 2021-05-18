package services;

import entities.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    public List<Employee> readDataForEmployees(String dataPath) throws IOException, ParseException {
        JSONArray jsonArray = createJsonArray(dataPath);

        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            Employee e = createEmployee(jsonArray, i);

            employees.add(e);
        }

        return employees;

    }

    private Employee createEmployee(JSONArray jsonArray, int i) {
        JSONObject employee = (JSONObject) jsonArray.get(i);

        String name = (String) employee.get("name");
        int salesPeriod = Integer.parseInt(employee.get("salesPeriod").toString());
        int totalSales = Integer.parseInt(employee.get("totalSales").toString());
        double experienceMultiplier = Double.parseDouble(employee.get("experienceMultiplier").toString());

        return new Employee(name, salesPeriod, totalSales, experienceMultiplier);
    }

    private JSONArray createJsonArray(String dataPath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(dataPath));

        JSONArray jsonArray = (JSONArray) object;
        return jsonArray;
    }

    public void calculateScore(List<Employee> employees, boolean useExperienceMultiplier) {
        for (Employee employee : employees) {
            calculateScoreForEmployee(employee, useExperienceMultiplier);
        }
    }

    public double calculateScoreForEmployee(Employee employee, boolean isUseExperienceMultiplier) {

        double score = 1.0* employee.getTotalSales() / employee.getSalesPeriod();
        if (isUseExperienceMultiplier){
            score *= employee.getExperienceMultiplier();
        }

        employee.setScore(score);
        return score;
    }
}
