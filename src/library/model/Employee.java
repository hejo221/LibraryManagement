package library.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Employee {
    private static final int MIN_EMPLOYEE_ID = 100;
    private static final int MAX_EMPLOYEE_ID = 999;
    private static final Set<Integer> allocatedEmployeeIDs = new HashSet<>();
    private static final Random random = new Random();

    private int employeeID;
    private String firstName;
    private String familyName;
    private double salary;
    private EmployeeStatus employeeStatus;
    private String password;

    public Employee(String firstName, String familyName, double salary, EmployeeStatus employeeStatus, String password) {
        this.employeeID = generateEmployeeID();
        this.firstName = firstName;
        this.familyName = familyName;
        this.salary = salary;
        this.employeeStatus = employeeStatus;
        this.password = password;
    }

    public Employee(int employeeID, String firstName, String familyName, double salary, EmployeeStatus employeeStatus, String password) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.familyName = familyName;
        this.salary = salary;
        this.employeeStatus = employeeStatus;
        this.password = password;
    }

    private int generateEmployeeID() {
        int randomID;

        do {
            randomID = random.nextInt(MAX_EMPLOYEE_ID - MIN_EMPLOYEE_ID + 1) + MIN_EMPLOYEE_ID;
        } while (allocatedEmployeeIDs.contains(randomID));

        allocatedEmployeeIDs.add(randomID);
        return randomID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Employee readFromFile(String employeeString) {
        String[] parts = employeeString.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid data format.");
        }

        int employeeID = Integer.parseInt(parts[0]);
        String firstName = parts[1];
        String familyName = parts[2];
        double salary = Double.parseDouble(parts[3]);
        String status = parts[4];
        String password = parts[5];

        EmployeeStatus employeeStatus = EmployeeStatus.valueOf(status);

        return new Employee(employeeID, firstName, familyName, salary, employeeStatus, password);
    }

    public String writeToFile() {
        return getEmployeeID() + "," + getFirstName() + "," + getFamilyName() + "," + getSalary() + "," + getEmployeeStatus() + "," + getPassword();
    }

    @Override
    public String toString() {
        return "Employee - Employee ID: " + getEmployeeID() + ", First Name: " + getFirstName() + ", Family Name: " + getFamilyName()
                + ", Employee Status: " + getEmployeeStatus() + ", Salary: " + getSalary();
    }

    public enum EmployeeStatus {
        INTERN, EMPLOYEE, MANAGER
    }
}
