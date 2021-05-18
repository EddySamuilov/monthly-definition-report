package services;

import entities.Employee;
import entities.Report;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportService {

    public Report generate(String reportDefinitionPath) throws IOException, ParseException {
        JSONObject jsonObject = createJsonObject(reportDefinitionPath);
        Report report = createReport(jsonObject);
        return report;

    }

    private Report createReport(JSONObject jsonObject) {
        int topPerformersThreshold = Integer.parseInt(jsonObject.get("topPerformersThreshold").toString());
        boolean useExperienceMultiplier = Boolean.parseBoolean(jsonObject.get("useExperienceMultiplier").toString());
        int periodLimit = Integer.parseInt(jsonObject.get("periodLimit").toString());

        return new Report(topPerformersThreshold, useExperienceMultiplier, periodLimit);
    }

    private JSONObject createJsonObject(String reportDefinitionPath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(reportDefinitionPath));

        JSONObject jsonObject = (JSONObject) object;
        return jsonObject;
    }

    public void generateCSV(List<Employee> employees, Report report, double minScoreBound) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/report.txt"));

        String output = this.makeOutput(employees, report, minScoreBound);

        writer.write(output);

        writer.close();
    }

//    public String convertToCSV(String[] data) {
//        return Stream.of(data)
//                .map(this::escapeSpecialCharacters)
//                .collect(Collectors.joining(","));
//    }
//
//    private String escapeSpecialCharacters(String data) {
//        String escapedData = data.replaceAll("\\R", " ");
//        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
//            data = data.replace("\"", "\"\"");
//            escapedData = "\"" + data + "\"";
//        }
//        return escapedData;
//    }

    private String makeOutput(List<Employee> employees, Report report, double minScoreBound) {
        StringBuilder sb = new StringBuilder("Name , Score").append(System.lineSeparator());

        for (Employee employee : employees) {
            if (employee.getSalesPeriod() <= report.getPeriodLimit() && employee.getScore() >= minScoreBound) {
                sb.append(employee.toString())
                        .append(System.lineSeparator());
            }
        }


        return sb.toString();
    }


    public double minScoreBound(List<Employee> employees, Report report) {

        double thresholdInPercent = 1.0 * report.getTopPerformersThreshold() / 100;

        double minimumScore = 0;
        for (Employee employee : employees) {
            minimumScore += employee.getScore();
        }

        double minBound = minimumScore * thresholdInPercent;
        return minimumScore - minBound;
    }
}
