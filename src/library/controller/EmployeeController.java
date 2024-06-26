package library.controller;

import library.exceptions.FileReadException;
import library.exceptions.FileWriteException;
import library.model.Customer;
import library.model.Employee;
import library.model.Employee.EmployeeStatus;
import library.util.ReadUtil;
import library.util.WriteUtil;

import java.util.HashMap;

public class EmployeeController {
    private HashMap<Integer, Employee> employeeDB;
    private Integer loggedInEmployeeID;

    public EmployeeController() {
        this.employeeDB = new HashMap<>();
        this.loggedInEmployeeID = null;
        readEmployees();
    }

    private void readEmployees() {
        try {
            employeeDB = ReadUtil.readEmployeesFromFile();
        } catch (FileReadException exception) {
            System.out.println("Error reading employees from file: " + exception.getMessage());
        }
    }

    public void printAllEmployees(HashMap<Integer, Employee> employeeDB) {
        if (employeeDB.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee employee : employeeDB.values()) {
                System.out.println(employee);
            }
        }
    }

    public Employee addNewEmployee(String firstName, String familyName, double salary, EmployeeStatus employeeStatus, String password) {
        Employee newEmployee = new Employee(firstName, familyName, salary, employeeStatus, password);
        employeeDB.put(newEmployee.getEmployeeID(), newEmployee);
        return newEmployee;
    }

    public Employee addNewEmployee(int employeeID, String firstName, String familyName, double salary, EmployeeStatus employeeStatus, String password) {
        Employee newEmployee = new Employee(employeeID, firstName, familyName, salary, employeeStatus, password);
        employeeDB.put(newEmployee.getEmployeeID(), newEmployee);
        return newEmployee;
    }

    public boolean updateEmployeeName(int id, String newFirstName, String newFamilyName) {
        if (employeeDB.containsKey(id)) {
            Employee employee = employeeDB.get(id);
            employee.setFirstName(newFirstName);
            employee.setFamilyName(newFamilyName);
            return true;
        }
        return false;
    }

    public boolean updateEmployeeSalary(int id, double newSalary) {
        if (employeeDB.containsKey(id)) {
            Employee employee = employeeDB.get(id);
            employee.setSalary(newSalary);
            return true;
        }
        return false;
    }

    public boolean updateEmployeeStatus(int id, EmployeeStatus newEmployeeStatus) {
        if (employeeDB.containsKey(id)) {
            Employee employee = employeeDB.get(id);
            employee.setEmployeeStatus(newEmployeeStatus);
            return true;
        }
        return false;
    }

    public boolean updateEmployeePassword(int id, String password) {
        if (employeeDB.containsKey(id)) {
            Employee employee = employeeDB.get(id);
            employee.setPassword(password);
            return true;
        }
        return false;
    }

    public Employee findEmployee(int id) {
        return employeeDB.get(id);
    }

    public boolean removeEmployee(int id) {
        if (employeeDB.containsKey(id)) {
            employeeDB.remove(id);
            return true;
        }
        return false;
    }

    public HashMap<Integer, Employee> getEmployeeDB() {
        return employeeDB;
    }

    public boolean login(int id, String password) {
        if (employeeDB.containsKey(id)) {
            Employee employee = employeeDB.get(id);
            if (employee.getPassword().equals(password)) {
                loggedInEmployeeID = id;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        loggedInEmployeeID = null;
    }

    public Employee getLoggedInEmployee() {
        if (loggedInEmployeeID != null) {
            return employeeDB.get(loggedInEmployeeID);
        } else {
            return null;
        }
    }
}
