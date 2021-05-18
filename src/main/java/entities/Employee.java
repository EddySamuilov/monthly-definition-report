package entities;

public class Employee {
    private String name;
    private int salesPeriod;
    private int totalSales;
    private double experienceMultiplier;
    private double score;

    public Employee() {
    }

    public Employee(String name, int salesPeriod, int totalSales, double experienceMultiplier) {
        this.name = name;
        this.salesPeriod = salesPeriod;
        this.totalSales = totalSales;
        this.experienceMultiplier = experienceMultiplier;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getSalesPeriod() {
        return salesPeriod;
    }


    public int getTotalSales() {
        return totalSales;
    }


    public double getExperienceMultiplier() {
        return experienceMultiplier;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("%s, %.2f", this.name, this.score);
    }
}
