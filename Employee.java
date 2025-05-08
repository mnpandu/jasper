public class Employee {
    private String employeeName;
    private int age;
    private double salary;

    public Employee(String employeeName, int age, double salary) {
        this.employeeName = employeeName;
        this.age = age;
        this.salary = salary;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }
}
