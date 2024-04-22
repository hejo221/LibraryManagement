package library.controller;

import library.model.Employee;
import library.model.Employee.EmployeeStatus;
import java.util.HashMap;

public class EmployeeController {
    private HashMap<Integer, Employee> employeeDB;

    public EmployeeController() {
        this.employeeDB = new HashMap<>();
    }

    public Employee addNewEmployee(String firstName, String familyName, double salary, EmployeeStatus employeeStatus) {
        Employee newEmployee = new Employee(firstName, familyName, salary, employeeStatus);
        employeeDB.put(newEmployee.getEmployeeID(), newEmployee);
        return newEmployee;
    }

    public boolean updateEmployee(int id, String newFirstName, String newFamilyName, double newSalary) {
        if (employeeDB.containsKey(id)) {
            Employee employee = employeeDB.get(id);
            employee.setFirstName(newFirstName);
            employee.setFamilyName(newFamilyName);
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
}
