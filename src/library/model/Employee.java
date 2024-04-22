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

    public Employee(String firstName, String familyName, double salary, EmployeeStatus employeeStatus) {
        this.employeeID = generateID();
        this.firstName = firstName;
        this.familyName = familyName;
        this.salary = salary;
        this.employeeStatus = employeeStatus;
    }

    private int generateID() {
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

    public enum EmployeeStatus {
        INTERN, NORMAL, MANAGER
    }
}