package dev.simplesolution.pdf.demo;

public class EmployeeNew {
    private int empID;
    private String name;
    private String department;
    private double salary;
    private String address;

    public EmployeeNew(int empID, String name, String department, double salary, String address) {
        this.empID = empID;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.address = address;
    }

    // Getters
    public int getEmpID() { return empID; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public String getAddress() { return address; }
}
